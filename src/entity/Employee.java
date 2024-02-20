package entity;

import java.util.List;

public class Employee {
    private int id;
    private String name;
    private boolean ifTookBook;

    private List<Integer> booksIssued;

    public List<Integer> getBooksIssued() {
        return booksIssued;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isIfTookBook() {
        return ifTookBook;
    }
}
