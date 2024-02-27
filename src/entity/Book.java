package entity;

public class Book {

    private int id;
    private String name;
    private String author;
    private String imagePath;
    private boolean isGiven;

    private String description;
    public Book(int id, String name, String author, String imagePath, boolean isGiven) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.imagePath = imagePath;
        this.isGiven = isGiven;
    }

    public Book(int id, String name, String author, String imagePath, boolean isGiven, String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.imagePath = imagePath;
        this.isGiven = isGiven;
        this.description = description;
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

    public String getImagePath() {
        return imagePath;
    }

    public boolean isGiven() {
        return isGiven;
    }

    public void setGiven(boolean given) {
        isGiven = given;
    }
}
