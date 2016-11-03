package com.example.len_001.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Len_001 on 11/3/2016.
 */

public class UserContentProvider extends ContentProvider {
    private UserSqLiteHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new UserSqLiteHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionargs, String sortOrder) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor returnCursor;
        returnCursor = database.query(UserDatabase.TABLE_NAME, projection, selection, selectionargs, null, null, sortOrder);
        returnCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return returnCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Uri returnUri;
        long id = database.insert(UserDatabase.TABLE_NAME, null, contentValues);
        if (id > 0)
            returnUri = UserDatabase.buildMessageUri(id);
        else throw new android.database.SQLException("Failed to insert row into " + uri);
        notifyChange(uri);
        return returnUri;
    }

    private void notifyChange(Uri uri) {
        if (getContext() != null)
            getContext().getContentResolver().notifyChange(uri, null);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int result = 0;
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        result = sqLiteDatabase.delete(UserDatabase.TABLE_NAME, selection, selectionArgs);
        sqLiteDatabase.close();
        if (result == -1)
            throw new IllegalArgumentException("Unknown URI " + uri);
        notifyChange(uri);
        return result;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        int rowsupdated = 0;
        rowsupdated=database.update(UserDatabase.TABLE_NAME,contentValues,s,strings);
        if(rowsupdated!=0)
            notifyChange(uri);
        return rowsupdated;
    }
}
