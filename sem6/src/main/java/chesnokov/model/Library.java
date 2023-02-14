package chesnokov.model;

import java.io.IOException;
import java.util.List;

public class Library {
    private final List<Book> books;

    public Library() throws IOException {
        books = new BooksFromTXT().getBookList(10);
    }

    public List<Book> getBookList(String title) {
        return books.stream()
                .filter(book -> book.getTitle().equals(title))
                .toList();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public List<Book> getAllBooks() {
        return books;
    }

}
