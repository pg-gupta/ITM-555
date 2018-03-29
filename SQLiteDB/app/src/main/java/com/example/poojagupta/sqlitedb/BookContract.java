package com.example.poojagupta.sqlitedb;

import android.provider.BaseColumns;

/**
 * Created by pooja gupta on 3/26/18.
 */

public final class BookContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private BookContract() {}

    /* Inner class that defines the table contents */
    public static class BookEntry implements BaseColumns {
        public static final String TABLE_NAME = "Book";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_AUTHOR = "author";
    }
    public static final String SQL_CREATE_TABLE="CREATE TABLE "+ BookEntry.TABLE_NAME+
            "("+BookEntry._ID+ " INTEGER PRIMARY KEY,"+BookEntry.COLUMN_NAME_TITLE+" TEXT,"
            +BookEntry.COLUMN_NAME_AUTHOR+" TEXT)";

    public static final String SQL_DELETE_TABLE="DROP TABLE IF EXISTS "+BookEntry.TABLE_NAME;
}
