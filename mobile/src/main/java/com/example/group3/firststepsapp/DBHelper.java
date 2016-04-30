package com.example.group3.firststepsapp;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Meetings.db";
    public static final String MEETINGS_TABLE_NAME = "saved_meetings";
    public static final String MEETINGS_COLUMN_KEY = "key";
    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table meetings " +
                        "(key integer primary key)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS meetings");
        onCreate(db);
    }

    public boolean insertContact(int key)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("key", key);
        db.insert("meetings", null, contentValues);
        return true;
    }

    public Cursor getData(int key){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from meetings where key="+key+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, MEETINGS_TABLE_NAME);
        return numRows;
    }

    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("meetings",
                "key = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<Integer> getAllSavedMeetings()
    {
        ArrayList<Integer> array_list = new ArrayList<Integer>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from meetings", null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            array_list.add(res.getInt(res.getColumnIndex(MEETINGS_COLUMN_KEY)));
            res.moveToNext();
        }
        return array_list;
    }
}
