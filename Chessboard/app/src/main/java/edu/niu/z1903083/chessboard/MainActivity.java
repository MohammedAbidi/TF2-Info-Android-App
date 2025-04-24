/************************************************************************
 *                                                                      *
 *  CSCI 322/522               Assignment 6                  Fall 2022  *
 *   Project Name: Chessboard                                           *
 *                                                                      *
 *     Class Name: MainActivity.java                                    *
 *                                                                      *
 *   Developer(s): Mohammed Abidi                                       *
 *                                                                      *
 *       Due Date: 12/2/2022                                            *
 *                                                                      *
 *        Purpose: Displays a chessboard that works                     *
 *                 in both vertical and horizontal orientations.        *
 *                                                                      *
 ************************************************************************/

package edu.niu.z1903083.chessboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {
    Chess chess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buildByCode();
    }

    // Create the instances and set their attributes
    public void buildByCode() {
        // Get Screen Size
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);

        // Get Smallest Side
        int x = size.x/8;
        int y = size.y/8;
        if (x > y) x = y;

        // Create Grid
        chess = new Chess(this, x);
        GridLayout gridLayout = chess.GetChessGrid();

        // Set Grid At Center
        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        constraintLayout.addView(gridLayout);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);
        constraintSet.connect(gridLayout.getId(), ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START,0);
        constraintSet.connect(gridLayout.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END,0);
        constraintSet.connect(gridLayout.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP,0);
        //constraintSet.connect(gridLayout.getId(), ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM,0);
        constraintSet.applyTo(constraintLayout);
        setContentView(constraintLayout);
    }

    // Checks the orientation of the screen
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
            chess.ChangeColor(1);
        else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
            chess.ChangeColor(2);
    }
}