/************************************************************************
 *                                                                      *
 *  CSCI 322/522               Assignment 4                  Fall 2022  *
 *   Project Name: Intents and Activities                               *
 *                                                                      *
 *     Class Name: SecondActivity.java                                  *
 *                                                                      *
 *   Developer(s): Mohammed Abidi                                       *
 *                                                                      *
 *       Due Date: 10/21/2022                                           *
 *                                                                      *
 *        Purpose: Displays information about the selected TF2 class.   *
 *                                                                      *
 ************************************************************************/

package edu.niu.z1903083.intentsandactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        updateView();
    }

    // Set new Descriptions for the button that was clicked
    public void updateView() {
        TextView nm = findViewById(R.id.nameDesc);
        nm.setText(TF2.getName());
        TextView hp = findViewById(R.id.healthDesc);
        hp.setText(TF2.getHealth());
        TextView sp = findViewById(R.id.speedDesc);
        sp.setText(TF2.getSpeed());
        TextView d1 = findViewById(R.id.desc1);
        d1.setText(TF2.getDesc1());
        TextView d2 = findViewById(R.id.desc2);
        d2.setText(TF2.getDesc2());
        TextView d3 = findViewById(R.id.desc3);
        d3.setText(TF2.getDesc3());
        ImageView img = findViewById(R.id.imageDesc);
        img.setImageResource(getResources().getIdentifier("tf2_" + TF2.n, "drawable", getPackageName()));
    }

    public void goBack(View v) {
        this.finish();
    }
}