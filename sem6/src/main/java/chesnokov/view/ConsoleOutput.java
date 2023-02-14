package chesnokov.view;

import chesnokov.model.Book;

import java.util.List;

public class ConsoleOutput {
    public void printBooks(List<Book> bookList) {
        System.out.println("-------------------------------------------------------------");
        System.out.println("Список книг:");
        for (int i = 0; i < bookList.size(); i++) {
            System.out.println((i + 1) + ": " + bookList.get(i));
        }
        System.out.println("-------------------------------------------------------------");
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

}
