package chesnokov.model;

import java.util.Map;
import java.util.TreeMap;

public class User implements BookStorage {
    private final Map<Book, Integer> treeBookMap = new TreeMap<>();

    @Override
    public Map<Book, Integer> getAllBooks() {
        return treeBookMap;
    }

    @Override
    public void addBook(Book book) {
        if (!treeBookMap.containsKey(book)) {
            treeBookMap.put(book, 1);
        } else {
            treeBookMap.put(book, treeBookMap.get(book) + 1);
        }
    }

    @Override
    public boolean removeBook(Book book) {
        if (treeBookMap.get(book) == null) {
            return false;
        }
        if (treeBookMap.get(book) == 1) {
            treeBookMap.remove(book);
        } else {
            treeBookMap.put(book, treeBookMap.get(book) - 1);
        }
        return true;
    }
}
