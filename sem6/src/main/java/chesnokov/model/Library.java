package chesnokov.model;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Library implements BookStorage {
    /**
     * Хранилище книг, где пара книга - кол-во вхождений, TreeMap используется для сортировки книг
     * сначала по автору, потом по названию
     */
    private final Map<Book, Integer> treeBookMap = new TreeMap<>();

    public void generateRandomBooks(Integer amountGenerate) throws IOException {
        List<Book> randomBookList = new RandomBook().getBookList(amountGenerate);
        addBookList(randomBookList);
    }

    @Override
    public void addBook(Book book) {
        if (!treeBookMap.containsKey(book)) {
            treeBookMap.put(book, 1);
        } else {
            treeBookMap.put(book, treeBookMap.get(book) + 1);
        }
    }

    public void addBookList(List<Book> bookList) {
        for (Book book : bookList) {
            addBook(book);
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

    @Override
    public Map<Book, Integer> getAllBooks() {
        return treeBookMap;
    }

}
