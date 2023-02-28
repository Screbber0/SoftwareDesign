package chesnokov.model;

import java.util.Map;

public interface BookStorage {
    void addBook(Book book);
    boolean removeBook(Book book);
    Map<Book, Integer> getAllBooks();
}
