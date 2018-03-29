package com.example.poojagupta.sqlitedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static List<String> ids=new ArrayList<>();
    public static List<String> books=new ArrayList<>();
    public static List<String> authors=new ArrayList<>();
    public static List<String> ratings=new ArrayList<>();
    ListView booksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SqlHelper db = null;
        try {
            db = new SqlHelper(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            db.getAllBooks();

            BookListAdapter bookAdapter = new BookListAdapter(this, ids, books, authors, ratings);
            booksList = (ListView) findViewById(R.id.booksList);
            booksList.setAdapter(bookAdapter);
        } catch (Exception e) {
            Toast.makeText(this, e.getStackTrace().toString(), Toast.LENGTH_SHORT).show();
        }

    }
}
