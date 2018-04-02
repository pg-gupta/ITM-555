package com.example.poojagupta.sqlitedbbase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * @author Pooja Gupta
 *         Date: 04/02/2018
 *         Lab: #7&8base
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("UserInfo", "*** Created by Pooja Gupta(A20413675) on April 2,2018 ***");
        SqlHelper db = null;

        try {
            db = new SqlHelper(this);

            // remove enteries if exist in the table
            db.deleteAllEnteries();
            // add books
            db.addBook(new Book("Professional Android 4 Application",
                    "Reto Meier"));
            db.addBook(new Book("Beginning Android 4 Application  Development",
                    "WeiMeng Lee"
            ));
            db.addBook(new Book("Programming Android", "Wallace Jackson"
            ));
            db.addBook(new Book("Hello, Android", "Wallace Jackson"));

            // get all the books
            List<Book> books = db.getAllBooks();
            // update the first book
            db.updateBook(books.get(0));
            // delete the first book
            db.deleteBook(books.get(0));
            // get all the books
            db.getAllBooks();

        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong!", Toast.LENGTH_LONG).show();
        } finally {
            db.closeDatabase();
        }

    }
}
