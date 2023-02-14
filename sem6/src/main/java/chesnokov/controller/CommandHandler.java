package chesnokov.controller;

import chesnokov.FacadeConsole;

public class CommandHandler {
    //TODO: подумать про интерфейс
    public void handleCommand(String line, FacadeConsole facade) {
        String[] command = line.trim().toLowerCase().split(" ", 2);
        if (command.length == 0 || (command[0].equals("/get") || command[0].equals("/put")) &&
                command.length != 2) {
            facade.incorrectCommand();
            return;
        }
        switch (command[0]) {
            case "/get" -> facade.getBook(command[1]);
            case "/put" -> facade.returnBook(command[1]);
            case "/list" -> facade.getAllUserBooks();
            case "/all" -> facade.getAllLibraryBook();
            default -> facade.incorrectCommand();
        }
    }
}
