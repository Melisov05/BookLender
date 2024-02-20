public class Book {

    private int id;
    private String name;
    private String author;
    private boolean isGiven;

    public Book(int id, String name, String author, boolean isGiven) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.isGiven = isGiven;
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
