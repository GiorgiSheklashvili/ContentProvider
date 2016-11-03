package com.example.len_001.contentprovider;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Len_001 on 11/3/2016.
 */

public class UserDatabase implements BaseColumns {
    public static final String DATABASE_NAME = "UserDatabase.db";
    public static final int DATABASE_VERSION = 1;
    public static final String PROVIDER_AUTHORITY = "com.example.len_001.contentprovider.provider.ContentProvider";
    public static final Uri BASE_CONTENT_URI=Uri.parse("content://"+PROVIDER_AUTHORITY+"/Users");
    public static final String TABLE_NAME = "Users";
    public static final String FIRST_NAME = "Name";
    public static final String LAST_NAME = "Users";
    public static final String _ID = "ID";

    public static Uri buildMessageUri(long id) {
        return ContentUris.withAppendedId(BASE_CONTENT_URI, id);
    }
}
