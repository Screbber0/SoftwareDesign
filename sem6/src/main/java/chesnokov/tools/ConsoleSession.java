package chesnokov.tools;

import chesnokov.FacadeConsole;
import chesnokov.controller.BaseController;

import java.util.Scanner;


public class ConsoleSession {
    private final FacadeConsole facade;
    private final Scanner scanner = new Scanner(System.in);
    private final static String GREETING_STRING = """
            Введите необходимую команду из списка:
            /get <name> - для получения книги начинающейся с <name>
            /list - для вывода всях взятых книг
            /put <name> - для того чтобы вернуть книгу начинающейся с <name>
            /all - для вывода всех книг в библиотеке
            /exit или /e для выхода""";

    private final static String FAREWELL_STRING = """
            Спасибо за пользование приложением!
            """;

    public ConsoleSession(FacadeConsole facade) {
        this.facade = facade;
    }

    public void startSession() {
        System.out.println(GREETING_STRING);
        String line = scanner.nextLine();
        while (!line.equals("/exit") && !line.equals("/e")) {
            BaseController.handleCommand(line, facade);
            line = scanner.nextLine();
        }
        System.out.println(FAREWELL_STRING);
    }
}
