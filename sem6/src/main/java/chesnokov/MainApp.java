package chesnokov;

import chesnokov.tools.ConsoleSession;

import java.io.IOException;

public class MainApp {
    public static void main(String[] args) throws IOException {
        FacadeConsole facadeConsole = new FacadeConsole();
        ConsoleSession consoleInput = new ConsoleSession(facadeConsole);
        consoleInput.startSession();
    }
}