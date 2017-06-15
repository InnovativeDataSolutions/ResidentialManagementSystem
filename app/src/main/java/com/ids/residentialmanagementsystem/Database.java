package com.ids.residentialmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zaid on 6/13/17.
 */
public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "rms.db";
    public static final String table = "admininfo";
    public static final String table2 = "homeinfo";

    public static final String col1ai = "fname";
    public static final String col2ai = "lname";
    public static final String col3ai = "email";
    public static final String col4ai = "mobile";

    public static final String col1hi = "homeid";
    public static final String col2hi = "peakdata";
    public static final String col3hi = "offpeakdata";
    public static final String col4hi = "peaktime";
    public static final String col5hi = "peaktime2";
    public static final String col6hi = "offpeaktime";
    public static final String col7hi = "offpeaktime2";
    public static final String col8hi = "brd";
    public static final String col9hi = "verificationcode";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_String = "CREATE TABLE " + table + "(" + col1ai + " TEXT," + col2ai + " TEXT," + col3ai + " TEXT," + col4ai + " TEXT" + ")";
        db.execSQL(SQL_String);
        String SQL_String2 = "CREATE TABLE " + table2 + "(" + col1hi + " TEXT," + col2hi + " TEXT," + col3hi + " TEXT," + col4hi + " TEXT," + col5hi + " TEXT," + col6hi + " TEXT," + col7hi + " TEXT," + col8hi + " TEXT," + col9hi + " TEXT" + ")";
        db.execSQL(SQL_String2);
     }

    //String SQL_String2 = "CREATE TABLE " + table2 + "(" + col1ui + " TEXT" + col2ui + " TEXT" + col3ui + " TEXT" + col4ui + " TEXT" + col5ui + " TEXT" + col6ui + " TEXT" + col7ui + " TEXT" + ")";

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + table);
        db.execSQL("DROP TABLE IF EXISTS" + table2);
        onCreate(db);

    }

    public boolean inserthomedetails(String homeid,String peakdata,String offpeakdata,String peaktime,String peaktime2,String offpeaktime,String offpeaktime2,String brd,String verficationid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1hi, homeid);
        contentValues.put(col2hi, peakdata);
        contentValues.put(col3hi, offpeakdata);
        contentValues.put(col4hi, peaktime);
        contentValues.put(col5hi, peaktime2);
        contentValues.put(col6hi, offpeaktime);
        contentValues.put(col7hi, offpeaktime2);
        contentValues.put(col8hi, brd);
        contentValues.put(col9hi, verficationid);
        Long result = db.insert(table2, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
}
