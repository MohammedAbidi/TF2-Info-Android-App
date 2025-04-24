package edu.niu.z1903083.recordstore;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.util.Log;
import android.graphics.Point;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private DatabaseManager dbManager;
    private double total;
    private ScrollView scrollView;
    private int buttonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DatabaseManager(this);
        total = 0.0;
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        buttonWidth = size.x / 2;
        updateView();
    }

    protected void onResume()
    {
        super.onResume();
        updateView();
    }

    public void updateView()
    {
        ArrayList<Record> records = dbManager.selectAll();
        if (records.size() > 0)
        {
            // remove subviews inside scrollView if necessary
            scrollView.removeAllViewsInLayout();

            // set up the grid layout
            GridLayout grid = new GridLayout(this);
            grid.setRowCount((records.size() + 1) / 2);
            grid.setColumnCount(2);

            // create array of buttons, 2 per row
            RecordButton [] buttons = new RecordButton[records.size()];
            ButtonHandler bh = new ButtonHandler();

            // fill the grid
            int i = 0;
            for (Record record : records)
            {
                // create the button
                buttons[i] = new RecordButton(this, record);
                buttons[i].setText(record.getName() + "\n" + record.getPrice());

                // set up event handling
                buttons[i].setOnClickListener(bh);

                // add the button to grid
                grid.addView(buttons[i], buttonWidth,
                        GridLayout.LayoutParams.WRAP_CONTENT);
                i++;
            }
            scrollView.addView(grid);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.action_add:
                Intent insertIntent
                        = new Intent(this, InsertActivity.class);
                this.startActivity(insertIntent);
                return true;
            case R.id.action_delete:
                Intent deleteIntent
                        = new Intent(this, DeleteActivity.class);
                this.startActivity(deleteIntent);
                return true;
            case R.id.action_update:
                Intent updateIntent
                        = new Intent(this, UpdateActivity.class);
                this.startActivity(updateIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class ButtonHandler implements View.OnClickListener
    {
        public void onClick(View v)
        {
            // retrieve price of the record and add it to total
            total += ((RecordButton) v).getPrice();
            String pay =
                    NumberFormat.getCurrencyInstance().format(total);
            Toast.makeText(MainActivity.this, pay, Toast.LENGTH_LONG).show();
        }
    }
}