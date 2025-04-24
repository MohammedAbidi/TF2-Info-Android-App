/************************************************************************
 *                                                                      *
 *  CSCI 322/522               Assignment 5                  Fall 2022  *
 *   Project Name: SQLite and Contacts List                             *
 *                                                                      *
 *     Class Name: Contact.java                                         *
 *                                                                      *
 *   Developer(s): Mohammed Abidi                                       *
 *                                                                      *
 *       Due Date: 11/11/2022                                           *
 *                                                                      *
 *        Purpose: Holds contact information.                           *
 *                                                                      *
 ************************************************************************/

package edu.niu.z1903083.sqliteandcontactslist;

public class Contact
{
    // For keeping track of which contact is currently selected.
    public static int currentId;

    // Variables
    private int id;
    private String first;
    private String last;
    private String email;
    private long phone;

    // Constructor
    public Contact(int newId, String newFirst, String newLast, String newEmail, long newPhone)
    {
        setId(newId);
        setFirstName(newFirst);
        setLastName(newLast);
        setEmail(newEmail);
        setPhoneNumber(newPhone);
    }

    // Set Methods
    public void setId(int newId)
    {
        id = newId;
    }

    public void setFirstName(String newFirst)
    {
        first = newFirst;
    }

    public void setLastName(String newLast)
    {
        last = newLast;
    }

    public void setEmail(String newEmail)
    {
        email = newEmail;
    }

    public void setPhoneNumber(long newPhone)
    {
        phone = newPhone;
    }

    // Get Methods
    public int getId()
    {
        return id;
    }

    public String getFirstName()
    {
        return first;
    }

    public String getLastName()
    {
        return last;
    }

    public String getEmail()
    {
        return email;
    }

    public long getPhoneNumber()
    {
        return phone;
    }

    public String getFullName()
    {
        return first + " " + last;
    }

    // Returns 1234567890 as (123) 456-7890 using String manipulation
    public String getPhoneFormatted()
    {
        return String.format("%010d", phone).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
    }

    public String toString()
    {
        return getFullName() + '\n' + email + '\n' + getPhoneFormatted();
    }
}