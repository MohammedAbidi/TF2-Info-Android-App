/************************************************************************
 *                                                                      *
 *  CSCI 322/522               Assignment 1                  Fall 2022  *
 *   Project Name: I Like Android (First Android Apps)                  *
 *                                                                      *
 *     Class Name: MainActivity.java                                    *
 *                                                                      *
 *   Developer(s): Mohammed Abidi                                       *
 *                                                                      *
 *       Due Date: 9/2/2022                                             *
 *                                                                      *
 *        Purpose: Set up the app to display "I like Android"           *
 *                                                                      *
 ************************************************************************/

package edu.niu.z1903083.ilikeandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}