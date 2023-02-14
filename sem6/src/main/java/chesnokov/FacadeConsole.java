package chesnokov;

import chesnokov.model.Book;
import chesnokov.model.Library;
import chesnokov.model.User;
import chesnokov.view.ConsoleOutput;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FacadeConsole {
    private final ConsoleInput consoleInput;
    private final ConsoleOutput consoleOutput;
    private final Library library;
    private final User user;

    public FacadeConsole() throws IOException {
        consoleOutput = new ConsoleOutput();
        consoleInput = new ConsoleInput();
        library = new Library();
        user = new User();
    }

    public void getBook(String title) {
        List<Book> bookList = library.getBookList(title);
        var a = bookList.stream()
                .collect(Collectors.groupingBy((Book::getAuthor),
                        Collectors.groupingBy(Book::getTitle)));
        if (bookList.size() == 0) {
            consoleOutput.printMessage("Книг с таким названием не найдено");
        } else if (bookList.size() == 1) {
            user.getBasket().add(bookList.get(0));
            library.removeBook(bookList.get(0));
        } else {
            int number = consoleInput.getNumberFromRange(1, bookList.size()) - 1;
            user.getBasket().add(bookList.get(number));
            library.removeBook(bookList.get(number));
        }
    }

    public void returnBook(String title) {
        library.addBook(new Book("test", "test"));
    }

    public void getAllLibraryBook() {
        consoleOutput.printBooks(library.getAllBooks());
    }

    public void getAllUserBooks() {
        consoleOutput.printBooks(user.getBasket());
    }

    public void incorrectCommand() {
        consoleOutput.printMessage("____Некорректная команда повторите попытку!____");
    }

}
