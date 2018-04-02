package com.example.poojagupta.sqlitedb;

/**
 * Created by miteshpatekar on 3/26/18.
 */

public class Book {
    int id;
    String title;
    String author;
    int ratings;

    Book(int id, String title, String author, int ratings) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.ratings = ratings;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return " [id=" + id + ",title=" + title + ",author=" + author + "]";
    }
}

