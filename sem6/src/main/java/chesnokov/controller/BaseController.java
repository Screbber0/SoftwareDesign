package chesnokov.controller;

import chesnokov.FacadeConsole;

public class BaseController {
    public static void handleCommand(String line, FacadeConsole facade) {
        String[] command = line.trim().toLowerCase().split(" +", 2);
        if (command.length == 0 ||
                (command[0].equals("/get") || command[0].equals("/put")) && command.length != 2) {
            facade.incorrectCommand();
            return;
        }

        switch (command[0]) {
            case "/get" -> facade.getLibraryBook(command[1]);
            case "/put" -> facade.returnBookToLibrary(command[1]);
            case "/list" -> facade.printConsoleAllUserBooks();
            case "/all" -> facade.printConsoleAllLibraryBook();
            default -> facade.incorrectCommand();
        }
    }
}
