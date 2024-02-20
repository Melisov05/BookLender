package server;


import com.sun.net.httpserver.HttpExchange;
import entity.Book;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.*;
import java.util.*;

public class HomeWorkServer extends BasicServer{
    private final static Configuration freemarker = initFreeMarker();

    public HomeWorkServer(String host, int port) throws IOException {
        super(host, port);
        registerGet("/books", this::booksHandler);
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

    private void booksHandler(HttpExchange exchange) {
        List<Book> books = getBooksData();
        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("books", books);
        renderTemplate(exchange, "books.html", dataModel);
    }



    private List<Book> getBooksData() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Java for Beginners", "Author A", false, 0));
        books.add(new Book(2, "Advanced Java Topics", "Author B", true, 1));
        books.add(new Book(2, "Clean code", "Author C", true, 3));
        return books;
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
