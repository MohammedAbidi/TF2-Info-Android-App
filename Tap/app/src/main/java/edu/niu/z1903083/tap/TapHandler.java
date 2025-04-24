/************************************************************************
 *                                                                      *
 *  CSCI 322/522               Assignment 6                  Fall 2022  *
 *   Project Name: Tap                                                  *
 *                                                                      *
 *     Class Name: TapHandler.java                                      *
 *                                                                      *
 *   Developer(s): Mohammed Abidi                                       *
 *                                                                      *
 *       Due Date: 12/2/2022                                            *
 *                                                                      *
 *        Purpose: To manage and keep track of tap events               *
 *                                                                      *
 ************************************************************************/

package edu.niu.z1903083.tap;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

public class TapHandler extends GestureDetector.SimpleOnGestureListener
{
    private static int n = 0;
    private TextView tapTV;
    private TextView countTV;
    private Context _this;

    public TapHandler(Context x, TextView t, TextView c)
    {
        _this = x;
        tapTV = t;
        countTV = c;
    }

    // Returns true if user tapped in tapTV
    private boolean IsEventInTapTV(MotionEvent event)
    {
        int touchX = (int) event.getRawX();
        int touchY = (int) event.getRawY();
        int left   = tapTV.getLeft();
        int top    = tapTV.getTop();
        int width  = tapTV.getWidth();
        int height = tapTV.getHeight();

        if ((touchX >= left && touchX <= left + width) && (touchY >= top && touchY <= top + height))
            return true;
        return false;
    }

    // Turn tapTV blue in single taps
    @Override
    public boolean onSingleTapConfirmed(MotionEvent event)
    {
        if (IsEventInTapTV(event))
        {
            n++;
            countTV.setText(Integer.toString(n));
            tapTV.setBackgroundColor(_this.getResources().getColor(R.color.blue));
        }
        return super.onSingleTapConfirmed(event);
    }

    // Turn tapTV red for double taps
    @Override
    public boolean onDoubleTap(MotionEvent event)
    {
        if (IsEventInTapTV(event))
        {
            n += 2;
            countTV.setText(Integer.toString(n));
            tapTV.setBackgroundColor(_this.getResources().getColor(R.color.red));
        }
        return super.onDoubleTap(event);
    }
}
