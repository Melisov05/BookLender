package server;

import entity.Book;

import java.util.List;

public class BooksDataModel {
    private List<Book> books;

    public BooksDataModel(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }


}
