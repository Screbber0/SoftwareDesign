package chesnokov.model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BookTest {
    @Test
    @DisplayName("Проверем две одинаковые для нас книги на равенство ожидаем, что они окажутся равными")
    public void test_book_equals_expect_true() {
        Book book1 = new Book("name", "author");
        Book book2 = new Book("name", "author");
        assertEquals(book1, book2);
    }

    @Test
    @DisplayName("Проверяем на неравенство две разные книгио жидаем, что они окажутся не равными")
    public void test_book_not_equals_expect_false() {
        Book book1 = new Book("name", "author");
        Book book2 = new Book("otherName", "author");
        assertNotEquals(book1, book2);
    }
}
