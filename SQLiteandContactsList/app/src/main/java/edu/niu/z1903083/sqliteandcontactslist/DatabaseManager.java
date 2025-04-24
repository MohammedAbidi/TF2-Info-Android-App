/************************************************************************
 *                                                                      *
 *  CSCI 322/522               Assignment 5                  Fall 2022  *
 *   Project Name: SQLite and Contacts List                             *
 *                                                                      *
 *     Class Name: DatabaseManager.java                                 *
 *                                                                      *
 *   Developer(s): Mohammed Abidi                                       *
 *                                                                      *
 *       Due Date: 11/11/2022                                           *
 *                                                                      *
 *        Purpose: Manages contacts using SQLite.                       *
 *                                                                      *
 ************************************************************************/

package edu.niu.z1903083.sqliteandcontactslist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "ContactsDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "contact";
    private static final String ID = "id";
    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String EMAIL = "email";
    private static final String PHONE_NUMBER = "phonenumber";

    public DatabaseManager(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        // build sql create statement
        String sqlCreate = "create table " + TABLE_NAME;
        sqlCreate +="(" + ID + " integer primary key autoincrement, ";
        sqlCreate += FIRST_NAME + " text, " + LAST_NAME + " text, ";
        sqlCreate += EMAIL + " text, " + PHONE_NUMBER + " integer)" ;

        db.execSQL(sqlCreate);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop old table if it exists
        db.execSQL("drop table if exists " + TABLE_NAME);
        // Re-create tables
        onCreate(db);
    }

    public void insert(Contact contact)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlInsert = "insert into " + TABLE_NAME;
        sqlInsert += " values(NULL, '" + contact.getFirstName();
        sqlInsert += "', '" + contact.getLastName();
        sqlInsert += "', '" + contact.getEmail();
        sqlInsert += "', '" + contact.getPhoneNumber() + "')";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void deleteById(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_NAME;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateById(int id, String firstName, String lastName, String email, long phoneNumber)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlUpdate = "update " + TABLE_NAME + " set ";
        sqlUpdate += FIRST_NAME + " = '" + firstName + "', ";
        sqlUpdate += LAST_NAME + " = '" + lastName + "', ";
        sqlUpdate += EMAIL + " = '" + email + "', ";
        sqlUpdate += PHONE_NUMBER + " = '" + phoneNumber;
        sqlUpdate += "' where " + ID + " = " + id;

        db.execSQL(sqlUpdate);
        db.close();
    }

    public Contact selectById(int id)
    {
        String sqlQuery = "select * from " + TABLE_NAME + (" where " + ID + " = " + id);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        Contact contact = null;
        if (cursor.moveToFirst())
            contact = new Contact(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getLong(4));

        return contact;
    }

    public ArrayList<Contact> selectAll()
    {
        String sqlQuery = "select * from " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Contact> records = new ArrayList<Contact>();
        while(cursor.moveToNext()) {
            records.add(new Contact(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getLong(4)));
        }

        db.close();
        return records;
    }
}