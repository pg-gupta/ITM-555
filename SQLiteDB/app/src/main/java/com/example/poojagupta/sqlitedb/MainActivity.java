package com.example.poojagupta.sqlitedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    public static List<String> ids = new ArrayList<>();
    public static List<String> books = new ArrayList<>();
    public static List<String> authors = new ArrayList<>();
    public static List<String> ratings = new ArrayList<>();
    ListView booksList;
    Spinner spinner;

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

        SqlHelper db = null;
        try {
            db = new SqlHelper(this);
            db.addBook(new Book(1,"Android Book","Brian",4));
            resetVariables();

            db.getAllBooks();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        /*try {

            db.getAllBooks();

            BookListAdapter bookAdapter = new BookListAdapter(this, ids, books, authors, ratings);
            booksList = (ListView) findViewById(R.id.booksList);
            booksList.setAdapter(bookAdapter);
        } catch (Exception e) {
            Toast.makeText(this, e.getStackTrace().toString(), Toast.LENGTH_SHORT).show();
        }*/
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, books);
        spinner.setAdapter(adapter);

        //final SqlHelper finalDb = db;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                SqlHelper finalDb = new SqlHelper(getApplicationContext());
                finalDb.getBook(books.get(position).toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
