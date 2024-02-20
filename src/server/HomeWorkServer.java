package server;

import com.sun.net.httpserver.HttpExchange;
import entity.Book;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeWorkServer extends BasicServer{
    private final static Configuration freemarker = initFreeMarker();

    public HomeWorkServer(String host, int port) throws IOException {
        super(host, port);
        registerGet("/books", this::booksHandler);
    }

    private static Configuration initFreeMarker() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        try {
            cfg.setDirectoryForTemplateLoading(new File("data"));
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
        Map<String, Object> libraryData = loadLibraryData(); // Загрузка данных из JSON
        renderTemplate(exchange, "books_list.ftl", libraryData);
    }

    // Метод для загрузки данных из JSON файла
    private Map<String, Object> loadLibraryData() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader("path/to/libraryData.json")) {
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            return gson.fromJson(reader, type);
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyMap();
        }
    }

    // Метод для рендеринга шаблонов
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
                exchange.sendResponseHeaders(500, -1); // Отправка кода ошибки 500
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
