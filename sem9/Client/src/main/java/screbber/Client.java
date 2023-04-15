package screbber;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;

    private final String name;
    private final BufferedReader reader;
    private final PrintWriter writer;

    private static final Logger logger = LogManager.getLogger(Client.class);

    public Client(String name, Socket socket) throws IOException {
        this.name = name;
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new PrintWriter(socket.getOutputStream(), true);
        logger.info("Client {} connected to server.", name);
    }

    public void start() throws IOException {
        Thread inputThread = new Thread(new UserInputHandler());
        inputThread.start();

        String message;
        while ((message = reader.readLine()) != null) {
            logger.info(message);
            System.out.println(message);
        }
    }

    private class UserInputHandler implements Runnable {
        @Override
        public void run() {
            try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
                String userInput;
                while ((userInput = consoleReader.readLine()) != null) {
                    writer.println(name + ": " + userInput);
                }
            } catch (IOException e) {
                logger.error("Error reading user input: {}", e.getMessage(), e);
            }
        }
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            System.out.println("Connected to server.");
            System.out.print("Enter your name: ");

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            String name = consoleReader.readLine();

            Client client = new Client(name, socket);
            client.start();
        } catch (IOException e) {
            logger.error("Error reading username: {}", e.getMessage(), e);
        }
    }
}
