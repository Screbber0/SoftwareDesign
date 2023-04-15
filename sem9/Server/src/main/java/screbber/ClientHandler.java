package screbber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final Server server;
    private final PrintWriter writer;
    private static final Logger logger = LogManager.getLogger(ClientHandler.class);

    public ClientHandler(Socket socket, Server server) throws IOException {
        this.clientSocket = socket;
        this.server = server;
        this.writer = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void run() {
        String clientIP = clientSocket.getInetAddress().getHostAddress();
        logger.info("New client connected from IP address: {}", clientIP);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String message;
            while ((message = reader.readLine()) != null) {
                logger.info("Received message from client with IP address {}: {}", clientIP, message);
                server.sendToAllClients(message, this);
            }
        } catch (IOException e) {
            logger.error("Error handling client with IP address {}: {}", clientIP, e.getMessage(), e);
            server.removeClient(this);
        }
    }

    public void sendMessage(String message) {
        try {
            writer.println(message);
            logger.info("Sent message to client with IP address {}: {}",
                    clientSocket.getInetAddress().getHostAddress(), message);
        } catch (Exception e) {
            logger.error("Error sending message to client with IP address {}: {}",
                    clientSocket.getInetAddress().getHostAddress(), e.getMessage(), e);
        }
    }
}
