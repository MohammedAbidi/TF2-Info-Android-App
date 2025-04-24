/************************************************************************
 *                                                                      *
 *  CSCI 322/522               Assignment 5                  Fall 2022  *
 *   Project Name: SQLite and Contacts List                             *
 *                                                                      *
 *     Class Name: InsertActivity.java                                  *
 *                                                                      *
 *   Developer(s): Mohammed Abidi                                       *
 *                                                                      *
 *       Due Date: 11/11/2022                                           *
 *                                                                      *
 *        Purpose: Activity that lets the user add a new contact        *
 *                                                                      *
 ************************************************************************/

package edu.niu.z1903083.sqliteandcontactslist;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class InsertActivity extends AppCompatActivity
{
    private DatabaseManager dbManager;

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        setContentView(R.layout.activity_insert);
    }

    public void insert(View v)
    {
        // Retrieve Contact Info
        EditText firstET = (EditText) findViewById(R.id.first_IET);
        EditText lastET = (EditText) findViewById(R.id.last_IET);
        EditText emailET = (EditText) findViewById(R.id.email_IET);
        EditText phoneET = (EditText) findViewById(R.id.phone_IET);

        String first = firstET.getText().toString();
        String last = lastET.getText().toString();
        String email = emailET.getText().toString();
        String phoneString = phoneET.getText().toString();

        // Check if Phone Number is Empty
        long phone = 0L;
        if (!phoneString.equals(""))
            phone = Long.parseLong(phoneString);

        // Insert new Contact into Database
        try
        {
            Contact contact = new Contact(0, first, last, email, phone);
            dbManager.insert(contact);
            Toast.makeText(this, contact.getFullName() + " Contact Created", Toast.LENGTH_SHORT).show();
            goBack(v);
        }
        catch(NumberFormatException nfe)
        {
            Toast.makeText(this, "Error Inserting Phone Number", Toast.LENGTH_LONG).show();
        }
    }

    public void goBack(View v)
    {
        this.finish();
    }
}