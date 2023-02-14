package chesnokov;

import java.util.Scanner;

public class ConsoleInput {
    private final Scanner scanner = new Scanner(System.in);

    private String command;

    public boolean enterCommand() {
        System.out.println("""
                Введите необходимую команду из списка:
                /get - для получения книги по названию
                /list - для вывода всях взятых книг
                /put - для того чтобы вернуть книгу по названию
                /all - для вывода всех книг в библиотеке
                /exit или /e для выхода""");
        String line = scanner.nextLine();
        if (line.equals("/exit") || line.equals("/e")) {
            return false;
        }
        command = line;
        return true;
    }

    public int getNumberFromRange(int start, int end) {
        System.out.println("Введите номер книги от " + start + " до "+ end);
        int number;
        String line;
        while (true) {
            try {
                line = scanner.nextLine();
                number = Integer.parseInt(line);
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Некорректно задано число, повторите попытку");
            }
        }
        return number;
    }

    public String getCommand() {
        return command;
    }
}
