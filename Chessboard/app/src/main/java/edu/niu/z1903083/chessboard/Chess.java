/************************************************************************
 *                                                                      *
 *  CSCI 322/522               Assignment 6                  Fall 2022  *
 *   Project Name: Chessboard                                           *
 *                                                                      *
 *     Class Name: Chess.java                                           *
 *                                                                      *
 *   Developer(s): Mohammed Abidi                                       *
 *                                                                      *
 *       Due Date: 12/2/2022                                            *
 *                                                                      *
 *        Purpose: To control and manage the chessboard.                *
 *                                                                      *
 ************************************************************************/

package edu.niu.z1903083.chessboard;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

public class Chess {
    private static final int[] STARTING_ROW = {R.string.rook_symbol, R.string.knight_symbol, R.string.bishop_symbol, R.string.queen_symbol, R.string.king_symbol, R.string.bishop_symbol, R.string.knight_symbol, R.string.rook_symbol};
    private int[] colors = {R.color.white, R.color.black, R.color.purple_500};
    private GridLayout chessboardGrid;
    private TextView[][] chessboardSquares = new TextView[8][8];

    public Chess(Context _this, int gridSize)
    {
        // Colors
        for (int i = 0; i < colors.length; i++)
            colors[i] = _this.getResources().getColor(colors[i]);

        // Create Grid
        chessboardGrid = new GridLayout(_this);
        chessboardGrid.setId(View.generateViewId());
        chessboardGrid.setColumnCount(8);
        chessboardGrid.setRowCount(8);
        chessboardGrid.setBackgroundColor(colors[0]);

        // Fill Grid with a checker pattern
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                TextView textView = new TextView(_this);
                textView.setId(View.generateViewId());
                textView.setTypeface(null, Typeface.BOLD);
                textView.setGravity(Gravity.CENTER);
                textView.setWidth(gridSize);
                textView.setHeight(gridSize);
                textView.setTextSize(40);
                chessboardSquares[x][y] = textView;

                if (x == 0 || x == 7)
                    textView.setText(STARTING_ROW[y]);
                else if (x == 1 || x == 6)
                    textView.setText(R.string.pawn_symbol);

                if ((x+y)%2 == 0) {
                    textView.setBackgroundColor(colors[1]);
                    textView.setTextColor(colors[0]);
                } else {
                    textView.setBackgroundColor(colors[0]);
                    textView.setTextColor(colors[1]);
                }

                chessboardGrid.addView(textView);
            }
        }
    }

    public GridLayout GetChessGrid()
    {
        return chessboardGrid;
    }

    public void ChangeColor(int colorCode)
    {
        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
                if ((x+y)%2 == 0)
                    chessboardSquares[x][y].setBackgroundColor(colors[colorCode]);
    }
}
