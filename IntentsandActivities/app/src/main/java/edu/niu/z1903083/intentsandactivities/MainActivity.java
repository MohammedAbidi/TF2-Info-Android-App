/************************************************************************
 *                                                                      *
 *  CSCI 322/522               Assignment 4                  Fall 2022  *
 *   Project Name: Intents and Activities                               *
 *                                                                      *
 *     Class Name: MainActivity.java                                    *
 *                                                                      *
 *   Developer(s): Mohammed Abidi                                       *
 *                                                                      *
 *       Due Date: 10/21/2022                                           *
 *                                                                      *
 *        Purpose: Display 9 different, but related images,             *
 *                 each of which contains additional information.       *
 *                                                                      *
 ************************************************************************/

package edu.niu.z1903083.intentsandactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    private static ImageButton[] buttons = new ImageButton[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupCode();
    }

    // Sets up instances and their attributes
    public void setupCode() {
        // Screen Size
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        int X = size.x/gridLayout.getRowCount();
        int Y = size.y/gridLayout.getColumnCount();
        if (X > Y) X = Y;

        // Buttons
        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageButton button = (ImageButton) gridLayout.getChildAt(i);
            buttons[i] = button;

            LayoutParams params2 = button.getLayoutParams();
            params2.height = X;
            params2.width = X;
            button.setLayoutParams(params2);
            button.setOnClickListener(new ButtonHandler());
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            for (int i = 0; i < buttons.length; i++)
                if (v == buttons[i]) {
                    showDesc(i);
                }
        }
    }

    public void showDesc(int i) {
        TF2.update(i);
        Intent intent = new Intent(this, SecondActivity.class);
        this.startActivity(intent);
    }
}