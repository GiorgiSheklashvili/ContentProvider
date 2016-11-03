package com.example.len_001.contentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Len_001 on 11/3/2016.
 */

public class UserSqLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS " + UserDatabase.TABLE_NAME
            + "(" + UserDatabase._ID + " INTEGER PRIMARY KEY," + UserDatabase.FIRST_NAME+ " TEXT, " + UserDatabase.LAST_NAME + " TEXT);";

    public UserSqLiteHelper(Context context) {
        super(context, UserDatabase.DATABASE_NAME, null, UserDatabase.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " +UserDatabase.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
