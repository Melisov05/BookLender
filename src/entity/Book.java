package entity;

import java.util.List;

public class Book {

    private int id;
    private String name;
    private String author;
    private boolean isGiven;

    private int issuedTo;


    public int getIssuedTo() {
        return issuedTo;
    }

    public Book(int id, String name, String author, boolean isGiven, int issuedTo) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.isGiven = isGiven;
        this.issuedTo = issuedTo;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isGiven() {
        return isGiven;
    }
}
