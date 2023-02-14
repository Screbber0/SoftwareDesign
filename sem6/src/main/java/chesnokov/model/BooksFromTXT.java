package chesnokov.model;

import java.io.IOException;
import java.util.List;

public class BooksFromTXT {
    private final RandomBook random;

    BooksFromTXT() throws IOException {
        random = new RandomBook();
    }

    public List<Book> getBookList(int size) {
        return random.getBookList(size);
    }

}
