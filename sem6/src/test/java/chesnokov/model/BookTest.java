package chesnokov.model;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BookTest {
    @Test
    @DisplayName("Проверем две одинаковые для нас книги на равенство")
    public void test_book_equals_expect_true() {
        Book book1 = new Book("name", "author");
        Book book2 = new Book("name", "author");
        assertEquals(book1, book2);
    }

    @Test
    @DisplayName("Проверяем на неравенство две разные книги")
    public void test_book_not_equals_expect_false() {
        Book book1 = new Book("name", "author");
        Book book2 = new Book("otherName", "author");
        assertNotEquals(book1, book2);
    }
}
