package com.ids.residentialmanagementsystem;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zaid on 6/13/17.
 */
public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "rms.db";
    public static final String table = "homeinfo";
    public static final String table2 = "userinfo";
    public static final String col1ui = "token";
    public static final String col2ui = "fname";
    public static final String col3ui = "lname";
    public static final String col4ui = "mobile";
    public static final String col5ui = "email";
    public static final String col6ui = "dob";
    public static final String col7ui = "passport";
    public static final String col8ui = "userid";
    public static final String col9ui = "title";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_String = "CREATE TABLE " + table + "(" + col2ui + " INTEGER" + ")";
        db.execSQL(SQL_String);
        String SQL_String2 = "CREATE TABLE " + table2 + "(" + col1ui + " TEXT," + col2ui + " TEXT," + col3ui + " TEXT," + col4ui + " TEXT," + col5ui + " TEXT," + col6ui + " TEXT," + col7ui + " TEXT," + col8ui + " TEXT," + col9ui + " TEXT" + ")";
        db.execSQL(SQL_String2);
     }

    //String SQL_String2 = "CREATE TABLE " + table2 + "(" + col1ui + " TEXT" + col2ui + " TEXT" + col3ui + " TEXT" + col4ui + " TEXT" + col5ui + " TEXT" + col6ui + " TEXT" + col7ui + " TEXT" + ")";

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + table);
        db.execSQL("DROP TABLE IF EXISTS" + table2);
        onCreate(db);

    }
}
