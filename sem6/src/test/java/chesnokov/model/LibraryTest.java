package chesnokov.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryTest {
    private static final String EMPTY_MESSAGE = "Библотека должна быть пустой при создании";

    @Test
    public void test_create_empty_library_expect_empty_map() {
        Library library = new Library();
        Assertions.assertEquals(Collections.EMPTY_MAP, library.getAllBooks());
    }

    @Test
    public void test_add_new_book_to_empty_library() {
        Library library = new Library();
        Assertions.assertEquals(Collections.EMPTY_MAP, library.getAllBooks(), EMPTY_MESSAGE);

        Book book = new Book("name", "author");
        library.addBook(book);

        Assertions.assertEquals(1, library.getAllBooks().size());

        Map<Book, Integer> expectedMap = new HashMap<>();
        expectedMap.put(book, 1);
        Assertions.assertEquals(expectedMap, library.getAllBooks());
    }

    @Test
    public void test_add_two_same_books_to_empty_library_expect_one_book_with_Integer_2() {
        Library library = new Library();
        Assertions.assertEquals(Collections.EMPTY_MAP, library.getAllBooks(), EMPTY_MESSAGE);

        Book book1 = new Book("name", "author");
        Book book2 = new Book("name", "author");
        library.addBookList(List.of(book1, book2));

        Assertions.assertEquals(1, library.getAllBooks().size());

        Map<Book,Integer> expextedMap = new HashMap<>();
        expextedMap.put(book1, 2);
        Assertions.assertEquals(expextedMap, library.getAllBooks());
    }

    @Test
    public void test_remove_book_from_empty_library() {
        Library library = new Library();
        Assertions.assertEquals(Collections.EMPTY_MAP, library.getAllBooks(), EMPTY_MESSAGE);
        Assertions.assertFalse(library.removeBook(new Book("name", "author")));
        Assertions.assertEquals(Collections.EMPTY_MAP, library.getAllBooks());
    }

    @Test
    public void test_remove_book_existing_in_library() {
        Library library = new Library();
        Assertions.assertEquals(Collections.EMPTY_MAP, library.getAllBooks(), EMPTY_MESSAGE);

        Book book = new Book("name", "author");
        library.addBook(book);
        Assertions.assertEquals(1, library.getAllBooks().size());


        Assertions.assertTrue(library.removeBook(book));
        Assertions.assertEquals(Collections.EMPTY_MAP, library.getAllBooks());
    }

    @Test
    public void test_remove_book_existing_in_two_copies_expect_book_with_amount_decrement_one() {
        Library library = new Library();
        Assertions.assertEquals(Collections.EMPTY_MAP, library.getAllBooks(), EMPTY_MESSAGE);

        Book book1 = new Book("name", "author");
        Book book2 = new Book("name", "author");
        library.addBookList(List.of(book1, book2));
        Assertions.assertEquals(1, library.getAllBooks().size());

        library.removeBook(book1);
        Map<Book,Integer> expextedMap = new HashMap<>();
        expextedMap.put(book2, 1);
        Assertions.assertEquals(expextedMap, library.getAllBooks());
    }

    @Test
    public void test_remove_book_not_existing_in_library() {
        Library library = new Library();
        Assertions.assertEquals(Collections.EMPTY_MAP, library.getAllBooks(), EMPTY_MESSAGE);

        Book book = new Book("name", "author");
        Book anotherBook = new Book("other", "other");
        library.addBook(anotherBook);
        Assertions.assertEquals(1, library.getAllBooks().size());

        Assertions.assertFalse(library.removeBook(book));
        Assertions.assertEquals(1, library.getAllBooks().size());
    }

    @Test
    public void test_generate_random_books_to_library_expect_count_generation_book() throws IOException {
        Library library = new Library();
        Assertions.assertEquals(Collections.EMPTY_MAP, library.getAllBooks(), EMPTY_MESSAGE);

        int count = 0;
        int amountGenerate = 10;
        library.generateRandomBooks(amountGenerate);
        for (Map.Entry<Book, Integer> entry : library.getAllBooks().entrySet()) {
            count += entry.getValue();
        }
        Assert.assertEquals(amountGenerate, count);
    }
}
