package com.example.poojagupta.sqlitedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

/**
 * @author Pooja Gupta
 * Date: 04/02/2018
 * Lab: #7
 */
public class MainActivity extends AppCompatActivity {

    public static List<String> ids = new ArrayList<>();
    public static List<String> books = new ArrayList<>();
    public static List<String> authors = new ArrayList<>();
    public static List<String> ratings = new ArrayList<>();
    ListView booksList;

    public static void resetVariables() {
        ids = new ArrayList<>();
        books = new ArrayList<>();
        authors = new ArrayList<>();
        ratings = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("UserInfo", "\n*** Created by Pooja Gupta(A20413675) on April 2,2018 ***\n");

        SqlHelper db = null;
        try {
            db = new SqlHelper(this);

            // remove enteries if exist in the table
            db.deleteAllEnteries();

            // add books
            db.addBook(new Book("Professional Android 4 Application",
                    "Reto Meier", 4));
            db.addBook(new Book("Beginning Android 4 Application  Development",
                    "WeiMeng Lee", 3
            ));
            db.addBook(new Book("Programming Android", "Wallace Jackson", 2
            ));
            db.addBook(new Book("Hello, Android", "Wallace Jackson", 5));

            resetVariables();
            // get all books
            db.getAllBooks();

            // bind data to book adapter
            BookListAdapter bookAdapter = new BookListAdapter(this, ids, books, authors, ratings);
            booksList = (ListView) findViewById(R.id.booksList);
            booksList.setAdapter(bookAdapter);

        } catch (IOException e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        } finally {
            db.close(); // close the database when done
        }

    }
}
