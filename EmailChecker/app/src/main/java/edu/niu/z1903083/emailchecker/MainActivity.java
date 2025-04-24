/************************************************************************
 *                                                                      *
 *  CSCI 322/522               Assignment 3                  Fall 2022  *
 *   Project Name: Coding the UI Programmatically                       *
 *                                                                      *
 *     Class Name: MainActivity.java                                    *
 *                                                                      *
 *   Developer(s): Mohammed Abidi                                       *
 *                                                                      *
 *       Due Date: 10/7/2022                                            *
 *                                                                      *
 *        Purpose: Asks the user to check if their email is valid       *
 *                                                                      *
 ************************************************************************/

package edu.niu.z1903083.emailchecker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private TextView label;
    private EditText field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildGuiByCode();
    }

    // Create the instances and set their attributes
    public void buildGuiByCode() {
        // Get Screen Size
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        int X = size.x;
        int Y = size.y/4;

        // Create the layout manager as a GridLayout
        GridLayout model = new GridLayout(this);
        model.setColumnCount(1);
        model.setRowCount(3);

        // Text Label
        label = new TextView(this);
        label.setWidth(X);
        label.setGravity(Gravity.CENTER);
        label.setTextSize(30);
        label.setBackgroundColor(Color.LTGRAY);
        label.setText(R.string.text_default);
        label.setTextAppearance(this, R.style.LabelStyle);
        model.addView(label, X, Y);

        // Text Field
        field = new EditText(this);
        field.setWidth(X);
        field.setGravity(Gravity.CENTER);
        field.setTextSize(30);
        field.setText(R.string.example_email);
        field.setTextAppearance(this, R.style.FieldStyle);
        model.addView(field, X, Y);

        // Button
        ButtonHandler bh = new ButtonHandler();
        button = new Button(this);
        button.setOnClickListener(bh);
        button.setText(R.string.button_text);
        button.setTextSize(24);
        button.setWidth(X);
        button.setTextColor(Color.WHITE);
        button.setBackgroundColor(Color.BLUE);
        model.addView(button, X, Y);

        setContentView(model);
    }

    // Displays button color to green if valid, red if not valid
    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            if (EmailChecker.isValid(field.getText())) {
                label.setBackgroundColor(Color.GREEN);
                label.setText(R.string.text_valid);
            } else {
                label.setBackgroundColor(Color.RED);
                label.setText(R.string.text_invalid);
            }
        }
    }
}