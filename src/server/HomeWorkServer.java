package server;


import com.sun.net.httpserver.HttpExchange;
import entity.Book;
import entity.Employee;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.management.ObjectName;
import java.io.*;
import java.util.*;

public class HomeWorkServer extends BasicServer{
    private final static Configuration freemarker = initFreeMarker();

    public HomeWorkServer(String host, int port) throws IOException {
        super(host, port);
        registerGet("/books", this::booksHandler);
        registerGet("/book", this::bookDetailHandler);
        registerGet("/profile", this::profileHandler);
    }

    private void profileHandler(HttpExchange exchange) {
        List<Employee> employeeList = getAllEmployeeData();
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("employees", employeeList);
        renderTemplate(exchange, "data/employees.ftlh", dataModel);
    }

    private List<Employee> getAllEmployeeData() {
        List<Employee> employees = new ArrayList<>();

        List<Book> currentBooksEmployee1 = Arrays.asList(
                new Book(1, "Harry Potter and the Philosopher's Stone", "J.K. Rowling", "/images/harrypotter1.jpg", true),
                new Book(2, "Harry Potter and the Chamber of Secrets", "J.K. Rowling", "/images/harrypotter2.jpg", true));
        List<Book> pastBooksEmployee1 = Arrays.asList(
                new Book(3, "The Hobbit", "J.R.R. Tolkien", "/images/hobbit.jpg", false));
        employees.add(new Employee(1, "John Doe", currentBooksEmployee1, pastBooksEmployee1));

        List<Book> currentBooksEmployee2 = Arrays.asList(
                new Book(4, "1984", "George Orwell", "/images/1984.jpg", true));
        List<Book> pastBooksEmployee2 = Arrays.asList(
                new Book(5, "Brave New World", "Aldous Huxley", "/images/bravenewworld.jpg", false),
                new Book(6, "Fahrenheit 451", "Ray Bradbury", "/images/fahrenheit451.jpg", false));
        employees.add(new Employee(2, "Jane Smith", currentBooksEmployee2, pastBooksEmployee2));

        List<Book> currentBooksEmployee3 = Arrays.asList(
                new Book(7, "The Catcher in the Rye", "J.D. Salinger", "/images/catcherintherye.jpg", true));
        List<Book> pastBooksEmployee3 = new ArrayList<>();
        employees.add(new Employee(3, "Alex Johnson", currentBooksEmployee3, pastBooksEmployee3));

        return employees;
    }


    private void bookDetailHandler(HttpExchange exchange) {
        List<Book> books = getBooksData();
        Book randomBook = books.get(new Random().nextInt(books.size()));
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("book", randomBook);
        renderTemplate(exchange, "data/book.ftlh", dataModel);
    }


    private void booksHandler(HttpExchange exchange) {
        List<Book> books = getBooksData();
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("books", books);
        renderTemplate(exchange, "data/books.ftlh", dataModel);
    }



    private List<Book> getBooksData() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Harry Potter: Philosopher's stone", "Rowling",
                "/images/harrypotter1.jpg", true));
        books.add(new Book(2, "Harry Potter: Chamber of Secrets", "Rowling",
                "/images/harrypotter2.jpg", true));
        books.add(new Book(3, "Harry Potter: Prizoner of Azkaban", "Rowling",
                "/images/harrypotter3.jpg", true));
        return books;
    }

    private static Configuration initFreeMarker() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        try {
            cfg.setDirectoryForTemplateLoading(new File("."));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);
            cfg.setFallbackOnNullLoopVariable(false);
        } catch (IOException e) {
            throw new RuntimeException("Failed to set directory for template loading", e);
        }
        return cfg;
    }

    protected void renderTemplate(HttpExchange exchange, String templateFile, Object dataModel) {
        try {
            Template template = freemarker.getTemplate(templateFile);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            try (OutputStreamWriter writer = new OutputStreamWriter(stream)) {
                template.process(dataModel, writer);
                writer.flush();
                byte[] data = stream.toByteArray();
                sendByteData(exchange, ResponseCodes.OK, ContentType.TEXT_HTML, data);
            }
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
            try {
                exchange.sendResponseHeaders(500, -1);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
