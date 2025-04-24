/************************************************************************
 *                                                                      *
 *  CSCI 322/522               Assignment 5                  Fall 2022  *
 *   Project Name: SQLite and Contacts List                             *
 *                                                                      *
 *     Class Name: MainActivity.java                                    *
 *                                                                      *
 *   Developer(s): Mohammed Abidi                                       *
 *                                                                      *
 *       Due Date: 11/11/2022                                           *
 *                                                                      *
 *        Purpose: Using SQLite to create, delete, and edit contacts.   *
 *                                                                      *
 ************************************************************************/

package edu.niu.z1903083.sqliteandcontactslist;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.graphics.Point;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.AdapterView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private DatabaseManager dbManager;
    private ScrollView scrollView;
    private GridLayout grid;
    private TextView searchLabel;
    private AutoCompleteTextView searchBar;
    private int buttonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        dbManager = new DatabaseManager(this);
        scrollView = new ScrollView(this);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        buttonWidth = size.x / 2;

        // 20 Examples
        if (dbManager.selectAll().size() == 0) {
            for (int i = 0; i < 20; i++) {
                dbManager.insert(new Contact(i, "User", Integer.toString(i), "user" + Integer.toString(i) + "@niu.edu", 1234567890L + Integer.toUnsignedLong(i)));
            }
        }

        setContentView(scrollView);
        updateView();
    }

    protected void onResume()
    {
        super.onResume();
        updateView();
    }

    public void updateView()
    {
        // Clear Everything
        scrollView.removeAllViewsInLayout();

        // Set Up Grid Layout (2 per row)
        ArrayList<Contact> contacts = dbManager.selectAll();
        grid = new GridLayout(this);
        grid.setRowCount((contacts.size()+1)/2 + 1);
        grid.setColumnCount(2);

        // Search Bar Label
        searchLabel = new TextView(this);
        searchLabel.setText(R.string.search_label);
        searchLabel.setTextSize(20);
        searchLabel.setGravity(Gravity.CENTER_HORIZONTAL);
        searchLabel.setTextColor(getResources().getColor(R.color.black));
        grid.addView(searchLabel, buttonWidth, GridLayout.LayoutParams.WRAP_CONTENT);

        // Search Bar with AutoComplete
        searchBar = new AutoCompleteTextView(this);
        searchBar.setThreshold(1);
        searchBar.setTextColor(getResources().getColor(R.color.black));
        grid.addView(searchBar, buttonWidth, GridLayout.LayoutParams.WRAP_CONTENT);

        // Create Contact Buttons
        Button[] buttons = new Button[contacts.size()];
        ArrayList<String> emails = new ArrayList<String>();
        int i = 0;
        for (Contact contact : contacts)
        {
            buttons[i] = new Button(this);
            buttons[i].setText(contact.toString());
            buttons[i].setOnClickListener(new ButtonHandler(contact.getId()));
            grid.addView(buttons[i], buttonWidth, GridLayout.LayoutParams.WRAP_CONTENT);
            emails.add(contact.getEmail());
            i++;
        }

        // Auto Complete Texts
        searchBar.setAdapter(new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, emails));
        searchBar.setOnItemClickListener(new AutoCompleteHandler());

        scrollView.addView(grid);
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
        this.startActivity(new Intent(this, InsertActivity.class));
        return true;
    }

    private class ButtonHandler implements View.OnClickListener
    {
        public int id;
        ButtonHandler(int newId)
        {
            id = newId;
        }

        public void onClick(View v)
        {
            // Save current id then start activity
            Contact.currentId = id;
            MainActivity.this.startActivity(new Intent(MainActivity.this, UpdateActivity.class));
        }
    }

    private class AutoCompleteHandler implements AdapterView.OnItemClickListener
    {
        public void onItemClick(AdapterView<?> parent, View view, int pos, long rowId) {
            // Hide Keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(view.getContext().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);

            // Search all contacts until the matching email is found
            ArrayList<Contact> contacts = dbManager.selectAll();
            for (Contact contact : contacts) {
                if (searchBar.getText().toString().equals(contact.getEmail())) {
                    // Clear everything except the Search Bar
                    grid.removeView(searchLabel);
                    grid.removeView(searchBar);
                    grid.removeAllViewsInLayout();
                    grid.addView(searchLabel, buttonWidth, GridLayout.LayoutParams.WRAP_CONTENT);
                    grid.addView(searchBar, buttonWidth, GridLayout.LayoutParams.WRAP_CONTENT);

                    // Create the new matching button
                    Button newButton = new Button(MainActivity.this);
                    newButton.setText(contact.toString());
                    newButton.setOnClickListener(new ButtonHandler(contact.getId()));
                    grid.addView(newButton, buttonWidth, GridLayout.LayoutParams.WRAP_CONTENT);

                    // Full Name Toast
                    Toast.makeText(MainActivity.this, contact.getFullName(), Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }
}