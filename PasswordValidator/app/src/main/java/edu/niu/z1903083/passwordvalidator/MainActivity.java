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
 *        Purpose: Asks the user to check if their password is strong.  *
 *                                                                      *
 ************************************************************************/

package edu.niu.z1903083.passwordvalidator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import android.view.Gravity;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.text.Editable;
import android.text.TextWatcher;

public class MainActivity extends AppCompatActivity {
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
        model.setRowCount(2);
        model.setBackgroundColor(Color.BLACK);

        // Text Label
        label = new TextView(this);
        label.setWidth(X);
        label.setGravity(Gravity.CENTER);
        label.setTextSize(30);
        label.setText(R.string.text_default);
        label.setBackgroundColor(Color.LTGRAY);
        model.addView(label, X, Y);

        // Text Field
        TextChangeHandler tch = new TextChangeHandler();
        field = new EditText(this);
        field.addTextChangedListener(tch);
        field.setWidth(X);
        field.setGravity(Gravity.CENTER);
        field.setTextSize(30);
        field.setBackgroundColor(Color.WHITE);
        model.addView(field, X, Y);

        setContentView(model);
    }

    // Updates as the user is typing if the password is weak or strong
    private class TextChangeHandler implements TextWatcher {
        public void afterTextChanged(Editable e) {
            String text = field.getText().toString();

            // Empty
            if (text.matches("")) {
                label.setBackgroundColor(Color.LTGRAY);
                label.setText(R.string.text_default);
                return;
            }

            // Strong
            if (PasswordChecker.isStrong(text)) {
                label.setBackgroundColor(Color.GREEN);
                label.setText(R.string.text_strong);
                return;
            }

            // Weak
            label.setBackgroundColor(Color.RED);
            label.setText(R.string.text_weak);
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        public void onTextChanged(CharSequence s, int start, int before, int after) {}
    }
}