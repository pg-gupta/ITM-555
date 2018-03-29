package com.example.poojagupta.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.*;

/**
 * Created by pooja gupta on 3/26/18.
 */

/*

public class SqlHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BookDB.sqlite";
    public static final String DB_LOCATION = "/data/data/com.example.poojagupta.sqlitedb/databases/";
    public static Context context;
    public static SQLiteDatabase mDatabase;

    public SqlHelper(Context context) {


        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       // sqLiteDatabase.execSQL(BookContract.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       // sqLiteDatabase.execSQL(BookContract.SQL_DELETE_TABLE);
       // onCreate(sqLiteDatabase);
    }

    public void openDatabase() {
        String dbPath = context.getDatabasePath(DATABASE_NAME).getPath();
        if (mDatabase!=null && mDatabase.isOpen()){
            return;
        }
            mDatabase=SQLiteDatabase.openDatabase(DB_LOCATION+DATABASE_NAME,null,SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase(){
        if(mDatabase!=null){
            mDatabase.close();
        }
    }
    */
/**
     * add a book to Book table in BookDB
     *
 *//*

    public void addBook(Book book) {

        SQLiteDatabase db = (new SqlHelper(context)).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(BookContract.BookEntry.COLUMN_NAME_TITLE, book.title);
        values.put(BookContract.BookEntry.COLUMN_NAME_AUTHOR, book.author);
        db.insert(BookContract.BookEntry.TABLE_NAME, null, values);
    }

    public void getAllBooks() {
       */
/* SQLiteDatabase db = (new SqlHelper(context)).getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                BookContract.BookEntry.COLUMN_NAME_TITLE,
                BookContract.BookEntry.COLUMN_NAME_AUTHOR
        };

        Log.i("title", "inside getall");

        Cursor cursor = db.query(BookContract.BookEntry.TABLE_NAME, projection, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Toast.makeText(context, String.valueOf(cursor.getCount()), Toast.LENGTH_SHORT).show();
        }*//*


        String[] projection = {
                BaseColumns._ID,
                BookContract.BookEntry.COLUMN_NAME_TITLE,
                BookContract.BookEntry.COLUMN_NAME_AUTHOR
        };
        Cursor cursor=  mDatabase.query(BookContract.BookEntry.TABLE_NAME,projection,null, null, null, null, null);
        while (cursor.moveToNext()) {
            Toast.makeText(context, cursor.getString(1), Toast.LENGTH_SHORT).show();
        };
    }

    public void updateBook(Book book) {

    }
}
*/


public class SqlHelper extends SQLiteOpenHelper {
    private Context mycontext;
    private static String DB_NAME = "BookDB.sqlite";
    private static String DB_PATH ="/data/data/"+BuildConfig.APPLICATION_ID+"/databases/";
    public SQLiteDatabase myDataBase;

    public SqlHelper(Context context) throws IOException {
        super(context,DB_NAME,null,1);
        this.mycontext=context;
        boolean dbexist = checkdatabase();
        if (dbexist) {
            System.out.println("Database exists");
            opendatabase();
        } else {
            System.out.println("Database doesn't exist");
            createdatabase();
        }
    }

    public void createdatabase() throws IOException {
        boolean dbexist = checkdatabase();
        if(dbexist) {
            System.out.println(" Database exists.");
        } else {

            this.getReadableDatabase();
            try {
                copydatabase();
            } catch(IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkdatabase() {

        boolean checkdb = false;
        try {
            String myPath = DB_PATH + DB_NAME;
            File dbfile = new File(myPath);
            checkdb = dbfile.exists();

        } catch(SQLiteException e) {
            System.out.println("Database doesn't exist");
        }
        return checkdb;
    }

    private void copydatabase() throws IOException {
        //Open your local db as the input stream
        InputStream myinput = mycontext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outfilename = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myoutput = new FileOutputStream(outfilename);

        // transfer byte to inputfile to outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer))>0) {
            myoutput.write(buffer,0,length);
        }

        //Close the streams
        myoutput.flush();
        myoutput.close();
        myinput.close();
    }

    public void opendatabase() throws SQLException {
        //Open the database
        String mypath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public synchronized void close() {
        if(myDataBase != null) {
            myDataBase.close();

        }
        super.close();
    }

    public void getAllBooks() {

        String[] projection = {
                BaseColumns._ID,
                BookContract.BookEntry.COLUMN_NAME_TITLE,
                BookContract.BookEntry.COLUMN_NAME_AUTHOR
        };
        Cursor cursor=  myDataBase.query(BookContract.BookEntry.TABLE_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext()) {
           MainActivity.ids.add(cursor.getString(0));
            MainActivity.books.add(cursor.getString(1));
            MainActivity.authors.add(cursor.getString(2));
            MainActivity.ratings.add(cursor.getString(3));

        };
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}