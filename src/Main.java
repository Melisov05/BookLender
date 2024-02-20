import server.HomeWorkServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            HomeWorkServer server = new HomeWorkServer("localhost", 9889);
            server.start();
            System.out.println("Server is started and listening on http://localhost:9889");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}