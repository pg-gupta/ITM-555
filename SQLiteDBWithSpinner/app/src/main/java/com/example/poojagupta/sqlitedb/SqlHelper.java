package com.example.poojagupta.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Pooja Gupta
 * Date: 04/02/2018
 * Lab: #8
 */

/**
 * Class contains all the operations which needs to be performed on the database
 */
public class SqlHelper extends SQLiteOpenHelper {

    // initialize database variables
    private static String DB_NAME = "BookDB.sqlite";
    private static String DB_PATH = "/data/data/" + BuildConfig.APPLICATION_ID + "/databases/";
    public SQLiteDatabase database;
    private Context mycontext;

    public SqlHelper(Context context) throws IOException {
        super(context, DB_NAME, null, 1);
        this.mycontext = context;
        boolean dbexist = checkdatabase();
        if (dbexist) {
            System.out.println("Database exists");
            opendatabase();
        } else {
            System.out.println("Database doesn't exist");
            createdatabase();
        }

    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(BookContract.SQL_CREATE_TABLE);
        Log.i("Table", "Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(BookContract.SQL_DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

    /**
     * add a book to Book table in BookDB
     */

    public void addBook(Book book) {

        ContentValues values = new ContentValues();
        values.put(BookContract.BookEntry.COLUMN_NAME_TITLE, book.getTitle());
        values.put(BookContract.BookEntry.COLUMN_NAME_AUTHOR, book.getAuthor());
        values.put(BookContract.BookEntry.COLUMN_NAME_RATING, book.getRatings());
        database.insert(BookContract.BookEntry.TABLE_NAME, null, values);
        Log.i("AddBook()", "\t" + book.toString());
    }

    /**
     * get all the books from the table
     *
     * @return list of books in the table
     */
    public List<Book> getAllBooks() {

        List<Book> books = new ArrayList<>();
        Book book;
        Cursor cursor = database.query(BookContract.BookEntry.TABLE_NAME, null, null, null, null, null, null);
        String booksString = "\t\t\t[";
        int index = 1;
        while (cursor.moveToNext()) {

            // add books to the list
            book = new Book();
            book.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.ID))));
            book.setTitle(cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.COLUMN_NAME_TITLE)));
            book.setAuthor(cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.COLUMN_NAME_AUTHOR)));
            book.setRatings(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.COLUMN_NAME_RATING))));
            books.add(book);

            // create seperate list of the books fields
            MainActivity.ids.add(String.valueOf(index++));
            MainActivity.books.add(cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.COLUMN_NAME_TITLE)));
            MainActivity.authors.add(cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.COLUMN_NAME_AUTHOR)));
            MainActivity.ratings.add(cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.COLUMN_NAME_RATING)));

            // books string to display in log
            booksString += "Book [ ";
            for (String col : cursor.getColumnNames()) {

                booksString += col + "=" + cursor.getString(cursor.getColumnIndex(col)) + " ";
            }
            booksString += "]\n\t\t\t\t\t\t ";
        }
        Log.i("GetAllBooks()", booksString + " ]");

        return books;
    }

    /**
     * update book in the database
     *
     * @param book book details to be updated
     * @return number of rows changed
     */
    public int updateBook(Book book) {

        ContentValues values = new ContentValues();
        values.put(BookContract.BookEntry.COLUMN_NAME_TITLE, book.getTitle());
        values.put(BookContract.BookEntry.COLUMN_NAME_AUTHOR, book.getAuthor());

        int rowsAffected = database.update(BookContract.BookEntry.TABLE_NAME, values,
                BookContract.BookEntry.ID.toString() + "=?", new String[]{String.valueOf(book.getId())});
        Log.i("UpdateBook()", book.toString());
        return rowsAffected;

    }

    /**
     * delete book from the database
     *
     * @param book book to be deleted
     */
    public void deleteBook(Book book) {
        database.delete(BookContract.BookEntry.TABLE_NAME,
                BookContract.BookEntry.ID + "=?", new String[]{String.valueOf(book.getId())});
        Log.i("DeleteBook()", book.toString());
    }

    /**
     * delete all the enteries from the table
     */
    public void deleteAllEnteries() {
        database.execSQL(BookContract.SQL_DELETE_ALLENTERIES);

    }

    public void createdatabase() throws IOException {
        boolean dbexist = checkdatabase();
        if (dbexist) {
            System.out.println(" Database exists.");
        } else {
            this.getReadableDatabase();
            try {
                copydatabase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    // check if the database  exists or not
    private boolean checkdatabase() {

        boolean checkdb = false;
        try {
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();

        } catch (SQLiteException e) {
            System.out.println("Database doesn't exist");
        }
        return checkdb;
    }

    // copy database from the file to the database path
    private void copydatabase() throws IOException {
        //Open your local db as the input stream
        InputStream myinput = mycontext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outfilename = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream(outfilename);

        // transfer byte from inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer)) > 0) {
            myoutput.write(buffer, 0, length);
        }

        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();
    }

    //open database and get the reference
    public void opendatabase() throws SQLException {
        //Open the database
        String mypath = DB_PATH + DB_NAME;
        database = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    /**
     * close database
     */
    public synchronized void close() {
        if (database != null) {
            database.close();
            Log.i("Database", "Close");

        }
        super.close();
    }

    /**
     * get book by name
     *
     * @param name name of the book to be fetched from database
     */
    public void getBook(String name) {
        Cursor cursor = database.query(BookContract.BookEntry.TABLE_NAME, null, "title='" + name + "'", null, null, null, null);
        cursor.moveToFirst();
        Book book = new Book();
        book.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.ID))));
        book.setTitle(cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.COLUMN_NAME_TITLE)));
        book.setAuthor(cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.COLUMN_NAME_AUTHOR)));
        book.setRatings(Integer.parseInt(cursor.getString(cursor.getColumnIndex(BookContract.BookEntry.COLUMN_NAME_RATING))));

        Log.i("getBook", book.toString());
        Toast.makeText(mycontext, "Author's name: " + cursor.getString(2) + "\nRatings= " + cursor.getString(3), Toast.LENGTH_LONG).show();
    }

}