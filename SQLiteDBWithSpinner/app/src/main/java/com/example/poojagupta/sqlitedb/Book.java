package com.example.poojagupta.sqlitedb;

/**
 * @author Pooja Gupta
 * Date: 04/02/2018
 * Lab: #8
 */


public class Book {
    int id;
    String title;
    String author;
    int ratings;

    Book() {
    }

    Book(String title, String author, int ratings) {
        this.title = title;
        this.author = author;
        this.ratings = ratings;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
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
        return " [id=" + getId() + ",title=" + getTitle() + ",author=" + getAuthor() + ", ratings=" + getRatings() + "]";
    }
}

