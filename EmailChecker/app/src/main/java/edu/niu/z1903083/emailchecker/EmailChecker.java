/************************************************************************
 *                                                                      *
 *  CSCI 322/522               Assignment 3                  Fall 2022  *
 *   Project Name: Coding the UI Programmatically                       *
 *                                                                      *
 *     Class Name: EmailChecker.java                                    *
 *                                                                      *
 *   Developer(s): Mohammed Abidi                                       *
 *                                                                      *
 *       Due Date: 10/7/2022                                            *
 *                                                                      *
 *        Purpose: Can check to see if the email input is valid.        *
 *                                                                      *
 ************************************************************************/

package edu.niu.z1903083.emailchecker;

public class EmailChecker {
    private static final char FIRST  = '@';
    private static final char SECOND = '.';

    // Returns true if at least both exist and the second is after the first.
    public static boolean isValid(String email) {
        int first = email.indexOf(FIRST);

        if (first == -1) return false;

        int second = email.indexOf(SECOND, first);

        if (second == -1) return false;

        return true;
    }

    // Same but accepts a CharSequence
    public static boolean isValid(CharSequence chars) {
        return isValid(chars.toString());
    }
}
