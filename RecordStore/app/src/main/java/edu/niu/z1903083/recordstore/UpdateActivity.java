package edu.niu.z1903083.recordstore;

import android.graphics.Point;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity
{
    DatabaseManager dbManager;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    // Build a View dynamically with all the records
    public void updateView()
    {
        ArrayList<Record> records = dbManager.selectAll();
        if (records.size() > 0)
        {
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView(this);
            GridLayout grid = new GridLayout(this);
            grid.setRowCount(records.size());
            grid.setColumnCount(4);

            // create arrays of components
            TextView [] ids = new TextView[records.size()];
            EditText [][] namesAndPrices = new EditText[records.size()][2];
            Button [] buttons = new Button[records.size()];
            ButtonHandler bh = new ButtonHandler();

            // retrieve width of screen
            Point size = new Point();
            getWindowManager().getDefaultDisplay().getSize(size);
            int width = size.x;

            int i = 0;

            for (Record record : records)
            {
                // create the TextView for the record's id
                ids[i] = new TextView(this);
                ids[i].setGravity(Gravity.CENTER);
                ids[i].setText("" + record.getId());

                // create the two EditTexts for the record's name and price
                namesAndPrices[i][0] = new EditText(this);
                namesAndPrices[i][1] = new EditText(this);
                namesAndPrices[i][0].setText(record.getName());
                namesAndPrices[i][1].setText("" + record.getPrice());
                namesAndPrices[i][1].setInputType(InputType.TYPE_CLASS_NUMBER);
                namesAndPrices[i][0].setId(10 * record.getId());
                namesAndPrices[i][1].setId(10 * record.getId() + 1);

                // create the button
                buttons[i] = new Button(this);
                buttons[i].setText("Update");
                buttons[i].setId(record.getId());

                // set up event handling
                buttons[i].setOnClickListener(bh);

                // add the elements to grid
                grid.addView(ids[i], width / 10,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndPrices[i][0], (int) (width * .4),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(namesAndPrices[i][1], (int) (width * .15),
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                grid.addView(buttons[i], (int) (width * .35),
                        ViewGroup.LayoutParams.WRAP_CONTENT);

                i++;
            }
            scrollView.addView(grid);
            setContentView(scrollView);
        }
    }

    private class ButtonHandler implements View.OnClickListener
    {
        public void onClick(View v)
        {
            // retrieve name and price of the record
            int recordId = v.getId();
            EditText nameET = (EditText) findViewById(10 * recordId);
            EditText priceET = (EditText) findViewById(10 * recordId + 1);
            String name = nameET.getText().toString();
            String priceString = priceET.getText().toString();

            // update record in database
            try
            {
                double price = Double.parseDouble(priceString);
                dbManager.updateById(recordId, name, price);
                Toast.makeText(UpdateActivity.this, "Record updated",
                        Toast.LENGTH_SHORT).show();

                // update screen
                updateView();
            }
            catch(NumberFormatException nfe)
            {
                Toast.makeText(UpdateActivity.this,
                        "Price error", Toast.LENGTH_LONG).show();
            }
        }
    }
}