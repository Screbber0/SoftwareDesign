package chesnokov.controller;

import chesnokov.model.Book;

import java.util.Map;
import java.util.TreeMap;

public class GetBookController {
    public static Map<Book, Integer> getBooksByTitle(String title, Map<Book, Integer> bookMap) {
        Map<Book, Integer> treeMap = new TreeMap<>();
        for (Map.Entry<Book, Integer> entry : bookMap.entrySet()) {
            Book book = entry.getKey();
            if (book.getTitle().toLowerCase().startsWith(title.toLowerCase())) {
                treeMap.put(book, entry.getValue());
            }
        }
        return treeMap;
    }
}
