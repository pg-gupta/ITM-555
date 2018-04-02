package com.example.poojagupta.sqlitedbbase;

/**
 * @author Pooja Gupta
 * Date: 04/02/2018
 * Lab: #7&8base
 */

public class Book {
    private int id;
    private String title;
    private String author;

    Book() {
    }

    Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return "\t\t\t Book [id = " + getId() + ",title = " + getTitle() + ",author = " + getAuthor() + "]";

    }
}

