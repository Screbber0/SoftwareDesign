package chesnokov.model;

public class Book implements Comparable<Book>{
    private final String title;
    private final String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;

        if (!getTitle().equals(book.getTitle())) return false;
        return getAuthor().equals(book.getAuthor());
    }

    @Override
    public int hashCode() {
        int result = getTitle().hashCode();
        result = 31 * result + getAuthor().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return author + ": \"" + title + "\"";
    }


    /**
     * Сравнивает объекоты книги сначала по автору, а затем по названию
     * @param other the object to be compared.
     */
    @Override
    public int compareTo(Book other) {
        if (!this.author.equals(other.author)) {
            return this.author.compareTo(other.author);
        }
        return this.title.compareTo(other.title);
    }
}
