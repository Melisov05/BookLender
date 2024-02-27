package entity;

import java.util.List;

public class Employee {

    private int id;
    private String name; // ФИО
    private List<Book> currentBooks;
    private List<Book> pastBooks;

    public Employee(int id, String name, List<Book> currentBooks, List<Book> pastBooks) {
        this.id = id;
        this.name = name;
        this.currentBooks = currentBooks;
        this.pastBooks = pastBooks;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Book> getCurrentBooks() {
        return currentBooks;
    }

    public List<Book> getPastBooks() {
        return pastBooks;
    }

    public void addCurrentBook(Book book) {
        currentBooks.add(book);
    }

    public void addPastBook(Book book) {
        pastBooks.add(book);
    }

    public void returnBook(Book book) {
        if (currentBooks.remove(book)) {
            pastBooks.add(book);
        }
    }
}
