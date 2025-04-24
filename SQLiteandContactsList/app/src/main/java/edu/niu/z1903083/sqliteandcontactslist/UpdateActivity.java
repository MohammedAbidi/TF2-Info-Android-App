/************************************************************************
 *                                                                      *
 *  CSCI 322/522               Assignment 5                  Fall 2022  *
 *   Project Name: SQLite and Contacts List                             *
 *                                                                      *
 *     Class Name: UpdateActivity.java                                  *
 *                                                                      *
 *   Developer(s): Mohammed Abidi                                       *
 *                                                                      *
 *       Due Date: 11/11/2022                                           *
 *                                                                      *
 *        Purpose: Lets the user update or delete the selected contact  *
 *                                                                      *
 ************************************************************************/

package edu.niu.z1903083.sqliteandcontactslist;

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
        setContentView(R.layout.activity_update);
        updateView();
    }

    // Build a View dynamically with all the records
    public void updateView()
    {
        // Retrieve and Update Contact Edit Texts
        EditText firstET = (EditText) findViewById(R.id.first_UET);
        EditText lastET = (EditText) findViewById(R.id.last_UET);
        EditText emailET = (EditText) findViewById(R.id.email_UET);
        EditText phoneET = (EditText) findViewById(R.id.phone_UET);

        Contact contact = dbManager.selectById(Contact.currentId);
        firstET.setText(contact.getFirstName());
        lastET.setText(contact.getLastName());
        emailET.setText(contact.getEmail());
        phoneET.setText(Long.toString(contact.getPhoneNumber()));
    }

    public void update(View v)
    {
        // Retrieve Contact Info
        EditText firstET = (EditText) findViewById(R.id.first_UET);
        EditText lastET = (EditText) findViewById(R.id.last_UET);
        EditText emailET = (EditText) findViewById(R.id.email_UET);
        EditText phoneET = (EditText) findViewById(R.id.phone_UET);

        String first = firstET.getText().toString();
        String last = lastET.getText().toString();
        String email = emailET.getText().toString();
        String phoneString = phoneET.getText().toString();

        // Check if Phone Number is Empty
        long phone = 0L;
        if (!phoneString.equals(""))
            phone = Long.parseLong(phoneString);

        // Update new Contact into Database
        try
        {
            dbManager.updateById(Contact.currentId, first, last, email, phone);
            Toast.makeText(this, "Contact Updated", Toast.LENGTH_SHORT).show();
            goBack(v);
        }
        catch(NumberFormatException nfe)
        {
            Toast.makeText(this, "Error Updating Phone Number", Toast.LENGTH_LONG).show();
        }
    }

    public void delete(View v)
    {
        dbManager.deleteById(Contact.currentId);
        Toast.makeText(this, "Contact Deleted", Toast.LENGTH_SHORT).show();
        goBack(v);
    }

    public void goBack(View v)
    {
        this.finish();
    }
}