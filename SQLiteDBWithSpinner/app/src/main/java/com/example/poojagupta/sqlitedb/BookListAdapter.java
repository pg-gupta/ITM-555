package com.example.poojagupta.sqlitedb;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * @author Pooja Gupta
 * Date: 04/02/2018
 * Lab: #8
 * /
 * /**
 * Custom BookAdapter to display books details
 */
public class BookListAdapter extends ArrayAdapter {

    public static Activity context;
    public static List<String> books;
    public static List<String> ids;
    public static List<String> authors;
    public static List<String> ratings;

    public BookListAdapter(Activity context, List<String> ids, List<String> books, List<String> authors, List<String> ratings) {
        super(context, R.layout.book_list, books);
        this.context = context;
        this.ids = ids;
        this.books = books;
        this.authors = authors;
        this.ratings = ratings;

    }

    // method populating values in the view of book_list
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.book_list, null, true);

        try {
            // getting reference of the views
            TextView id = (TextView) rowView.findViewById(R.id.IdTxtView);
            TextView book = (TextView) rowView.findViewById(R.id.bookNameTxtView);
            TextView author = (TextView) rowView.findViewById(R.id.authorTxtView);
            RatingBar rating = (RatingBar) rowView.findViewById(R.id.ratingBar);

            // setting the required values in the views
            id.setText(ids.get(position));
            book.setText(books.get(position));
            author.setText(authors.get(position));
            rating.setRating((float) Integer.parseInt(ratings.get(position)));

        } catch (Exception e) {
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
        return rowView;
    }
}
