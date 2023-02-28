package chesnokov.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomBook {
    private final List<String> titleList;
    private final List<String> authorList;

    private final String authorPath = "src/main/resources/authors.txt";
    private final String titlePath = "src/main/resources/titles.txt";
    private final BufferedReader authorBufferedReader;
    private final BufferedReader titleBufferedReader;

    public RandomBook() throws IOException {
        authorBufferedReader = new BufferedReader(new FileReader(authorPath));
        titleBufferedReader = new BufferedReader(new FileReader(titlePath));
        titleList = readAllTitles();
        authorList = readAllAuthors();
    }

    private List<String> readAllTitles() throws IOException {
        List<String> titleList = new ArrayList<>();
        try (titleBufferedReader) {
            String title;
            while ((title = titleBufferedReader.readLine()) != null) {
                titleList.add(title);
            }
        }
        return titleList;
    }

    private List<String> readAllAuthors() throws IOException {
        List<String> authorList = new ArrayList<>();
        try (authorBufferedReader) {
            String title;
            while ((title = authorBufferedReader.readLine()) != null) {
                authorList.add(title);
            }
        }
        return authorList;
    }

    public Book getRandomBook() {
        Random random = new Random();
        String title = titleList.get(random.nextInt(titleList.size()));
        String author = authorList.get(random.nextInt(authorList.size()));
        return new Book(title, author);
    }

    public List<Book> getBookList(int size) {
        List<Book> bookList = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            bookList.add(getRandomBook());
        }
        return bookList;
    }
}
