package com.example.poojagupta.sqlitedb;

import android.provider.BaseColumns;

/**
 * @author Pooja Gupta
 * Date: 04/02/2018
 * Lab: #8
 */
/**
 * Class which defines the 'Book' table details
 */
public final class BookContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private BookContract() {}

    /* Inner class that defines the table contents */
    public static class BookEntry implements BaseColumns {
        public static final String ID = "id";
        public static final String TABLE_NAME = "Book";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_AUTHOR = "author";
        public static final String COLUMN_NAME_RATING="ratings";
    }
    public static final String SQL_CREATE_TABLE="CREATE TABLE "+ BookEntry.TABLE_NAME+
            "("+BookEntry.ID+ " INTEGER PRIMARY KEY,"+BookEntry.COLUMN_NAME_TITLE+" TEXT,"
            +BookEntry.COLUMN_NAME_AUTHOR+" TEXT,"+BookEntry.COLUMN_NAME_RATING+" INTEGER)";

    public static final String SQL_DELETE_TABLE="DROP TABLE IF EXISTS "+BookEntry.TABLE_NAME;
    public static final String SQL_DELETE_ALLENTERIES = "DELETE FROM " + BookEntry.TABLE_NAME;
}
