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
 *        Purpose: Can check to see if the password input is strong.    *
 *                                                                      *
 ************************************************************************/

package edu.niu.z1903083.passwordvalidator;

public class PasswordChecker {
    private static final int MINIMUM_VALID_LENGTH = 9;

    // Returns true if the length of the password has 9 or more characters.
    public static boolean isStrong(String password) {
        return password.length() >= MINIMUM_VALID_LENGTH;
    }
}