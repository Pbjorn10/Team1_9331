package main.java.com.team1.ecommerce.server.net;

import main.java.com.team1.ecommerce.server.logging.ServerLogger;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class SocketServer {
    private int port;
    private boolean isRunning;
    private ServerSocket serverSocket;

    public SocketServer(int port) {
        this.port = port;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(port);
            isRunning = true;
            ServerLogger.info("Server started on port " + port);

            while (isRunning) {
                // CODE STOPS HERE and waits for a user
                Socket clientSocket = serverSocket.accept();

                ServerLogger.info("New client connected: " + clientSocket.getInetAddress());

                // Create a new worker for this specific user
                ClientHandler worker = new ClientHandler(clientSocket);

                // Launch the worker on a separate thread so we can go back to accept()
                new Thread(worker).start();
            }
        } catch (IOException e) {
            if (isRunning) {
                ServerLogger.error("Server crashed: " + e.getMessage());
            } else {
                ServerLogger.info("Server stopped.");
            }
        }
    }

    public void stop() {
        isRunning = false;
        try {
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

