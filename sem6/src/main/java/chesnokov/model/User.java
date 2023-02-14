package chesnokov.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final List<Book> basket;

    public User() {
        this.basket = new ArrayList<>();
    }

    public List<Book> getBasket() {
        return basket;
    }
}
