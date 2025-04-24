/************************************************************************
 *                                                                      *
 *  CSCI 322/522               Assignment 6                  Fall 2022  *
 *   Project Name: Tap                                                  *
 *                                                                      *
 *     Class Name: MainActivity.java                                    *
 *                                                                      *
 *   Developer(s): Mohammed Abidi                                       *
 *                                                                      *
 *       Due Date: 12/2/2022                                            *
 *                                                                      *
 *        Purpose: To detect taps and react accordingly.                *
 *                                                                      *
 ************************************************************************/

package edu.niu.z1903083.tap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up TapHandler
        TextView tapTV = (TextView) findViewById(R.id.tapTextView);
        TextView countTV = (TextView) findViewById(R.id.countTextView);
        detector = new GestureDetector(this, new TapHandler(this, tapTV, countTV));
    }

    // Set up Gesture Detector
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        return detector.onTouchEvent(event);
    }
}