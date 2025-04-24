package edu.niu.z1903083.recordstore;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseManager extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME = "recordDB";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_RECORD = "record";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PRICE = "price";

    public DatabaseManager(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db)
    {
        // build sql create statement
        String sqlCreate = "create table " + TABLE_RECORD + "(" + ID;
        sqlCreate += " integer primary key autoincrement, " + NAME;
        sqlCreate += " text, " + PRICE + " real)" ;

        db.execSQL(sqlCreate);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop old table if it exists
        db.execSQL("drop table if exists " + TABLE_RECORD);
        // Re-create tables
        onCreate(db);
    }

    public void insert(Record record)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlInsert = "insert into " + TABLE_RECORD;
        sqlInsert += " values(null, '" + record.getName();
        sqlInsert += "', '" + record.getPrice() + "')";

        db.execSQL(sqlInsert);
        db.close();
    }

    public void deleteById(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sqlDelete = "delete from " + TABLE_RECORD;
        sqlDelete += " where " + ID + " = " + id;

        db.execSQL(sqlDelete);
        db.close();
    }

    public void updateById(int id, String name, double price)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        String sqlUpdate = "update " + TABLE_RECORD;
        sqlUpdate += " set " + NAME + " = '" + name + "', ";
        sqlUpdate += PRICE + " = '" + price + "'";
        sqlUpdate += " where " + ID + " = " + id;

        db.execSQL(sqlUpdate);
        db.close();
    }

    public ArrayList<Record> selectAll()
    {
        String sqlQuery = "select * from " + TABLE_RECORD;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        ArrayList<Record> records = new ArrayList<Record>();
        while(cursor.moveToNext())
        {
            Record currentRecord
                    = new Record(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getDouble(2));
            records.add(currentRecord);
        }
        db.close();
        return records;
    }

    public Record selectById(int id)
    {
        String sqlQuery = "select * from " + TABLE_RECORD;
        sqlQuery += " where " + ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        Record record = null;
        if (cursor.moveToFirst())
            record = new Record(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getDouble(2));
        return record;
    }
}
