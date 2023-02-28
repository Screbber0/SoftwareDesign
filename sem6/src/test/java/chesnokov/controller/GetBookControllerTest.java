package chesnokov.controller;

import chesnokov.model.Book;
import chesnokov.model.Library;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class GetBookControllerTest {
    private static final String EMPTY_MESSAGE = "Библотека должна быть пустой при создании";

    @Test
    public void take_book_by_title_which_not_exit_in_library_expect_empty_map() {
        Library library = new Library();
        Assertions.assertEquals(Collections.EMPTY_MAP, library.getAllBooks(), EMPTY_MESSAGE);

        String title = "No exit name";
        Map<Book, Integer> bookMap = GetBookController.getBooksByTitle(title, library.getAllBooks());
        Assertions.assertEquals(Collections.EMPTY_MAP, bookMap);
    }

    @Test
    public void take_book_by_title_which_exit_in_library_expect_map_with_one_item() {
        Library library = new Library();
        Assertions.assertEquals(Collections.EMPTY_MAP, library.getAllBooks(), EMPTY_MESSAGE);

        Book book = new Book("как выпонять задания по параллелизму на одноядерном процессоре?",
                "С.А. Чесноков");
        library.addBook(book);
        Assertions.assertEquals(1, library.getAllBooks().size());

        String title = "как выпонять задания по параллелизму на одноядерном процессоре?";
        Map<Book, Integer> bookMap = GetBookController.getBooksByTitle(title, library.getAllBooks());
        Map<Book, Integer> expected = new TreeMap<>();
        expected.put(book, 1);

        Assertions.assertEquals(expected, bookMap);
    }
}
