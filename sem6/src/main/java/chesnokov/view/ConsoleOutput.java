package chesnokov.view;

import chesnokov.model.Book;

import java.util.Map;

public class ConsoleOutput {
    public void printBookMap(Map<Book, Integer> mapBook) {
        System.out.println("-------------------------------------------------------------");
        System.out.println("Список книг:");
        int counter = 1;
        for (Map.Entry<Book, Integer> entry : mapBook.entrySet()) {
            System.out.println(counter++ + " " + entry.getKey() + " " + entry.getValue() + "(шт.)");
        }
        System.out.println("-------------------------------------------------------------");
    }

}
