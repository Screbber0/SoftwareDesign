package chesnokov;

import chesnokov.controller.GetBookController;
import chesnokov.model.Book;
import chesnokov.model.Library;
import chesnokov.model.BookStorage;
import chesnokov.model.User;
import chesnokov.view.ConsoleOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FacadeConsole {
    private static final String INCORRECT_COMMAND = "____Некорректная команда повторите попытку!____";
    private final ConsoleOutput consoleOutput;
    Scanner scanner = new Scanner(System.in);
    private final Library library;
    private final User user;

    public FacadeConsole() throws IOException {
        consoleOutput = new ConsoleOutput();
        library = new Library();
        library.generateRandomBooks(5);
        user = new User();
    }

    /**
     * Выбирает номер книги, которая будет взята из мапы
     * @param treeBookMap treeMap домтупных книг
     * @return выбранная книга
     */
    private Book chooseBookFromMap(Map<Book, Integer> treeBookMap) {
        System.out.println("Введите номер книги от 1 до " + treeBookMap.size() +
                ", чтобы  выбрать интересующую вас книгу");
        int number;
        while (true) {
            try {
                number = Integer.parseInt(scanner.nextLine());
                if (number <= 0 || number > treeBookMap.size()) {
                    throw new NumberFormatException();
                } else {
                    break;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Некорректно задано число, повторите попытку");
            }
        }
        List<Map.Entry<Book, Integer>> entryList = new ArrayList<>(treeBookMap.entrySet());
        return entryList.get(number - 1).getKey();
    }

    private void transferBookFromTo(Book book, BookStorage from, BookStorage to) {
        from.removeBook(book);
        to.addBook(book);
    }

    /**
     * Получаем книгу по названию из бибилиотеки, если книга стаким названием только одна,
     * то автоматически будет взята
     * @param title название книги
     */
    public void getLibraryBook(String title) {
        var bookMap = GetBookController.getBooksByTitle(title, library.getAllBooks());
        if (bookMap.isEmpty()) {
            System.out.println("Книг с таким названием не найдено в библиотеке");
        } else if (bookMap.size() == 1) {
            for (Book book : bookMap.keySet()) {
                transferBookFromTo(book, library, user);
                System.out.println("Вы успешно взяли книгу: " + book);
                break;
            }
        } else {
            consoleOutput.printBookMap(bookMap);
            Book book = chooseBookFromMap(bookMap);
            System.out.println("Вы успешно взяли книгу: " + book);
            transferBookFromTo(book, library, user);
        }
    }

    /**
     * Возвращаем книгу по названию из бибилиотеки, если книга стаким названием только одна,
     * то автоматически будет возвращено толоько одна
     * @param title название книги
     */
    public void returnBookToLibrary(String title) {
        var bookMap = GetBookController.getBooksByTitle(title, user.getAllBooks());
        if (bookMap.isEmpty()) {
            System.out.println("У вас нет книги с таким названием");
        } else if (bookMap.size() == 1) {
            for (Book book : bookMap.keySet()) {
                transferBookFromTo(book, user, library);
                System.out.println("Вы успешно вернули книгу в библиотеку: " + book);
                break;
            }
        } else {
            consoleOutput.printBookMap(bookMap);
            Book book = chooseBookFromMap(bookMap);
            transferBookFromTo(book, user, library);
            System.out.println("Вы успешно вернули книгу в библиотеку: " + book);
        }
    }

    /**
     * Выводит в консоль все книги из библиотеки
     */
    public void printConsoleAllLibraryBook() {
        var bookMap = library.getAllBooks();
        if (bookMap.isEmpty()) {
            System.out.println("В библиотеке нет больше книг :(");
        } else {
            consoleOutput.printBookMap(library.getAllBooks());
        }
    }

    /**
     * Выыодит в консоль все книги пользователя
     */
    public void printConsoleAllUserBooks() {
        var bookMap = user.getAllBooks();
        if (bookMap.isEmpty()) {
            System.out.println("У вас нет взятых книг");
        } else {
            consoleOutput.printBookMap(user.getAllBooks());
        }
    }

    public void incorrectCommand() {
        System.out.println(INCORRECT_COMMAND);
    }

}
