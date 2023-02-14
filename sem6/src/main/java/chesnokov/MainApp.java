package chesnokov;

import chesnokov.controller.CommandHandler;
import chesnokov.model.RandomBook;

import java.io.IOException;
import java.io.InputStream;

public class MainApp {
    public static void main(String[] args) throws IOException {
        CommandHandler commandHandler = new CommandHandler();
        FacadeConsole facadeConsole = new FacadeConsole();

        ConsoleInput consoleInput = new ConsoleInput();
        while (consoleInput.enterCommand()) {
            commandHandler.handleCommand(consoleInput.getCommand(), facadeConsole);
        }
    }
}