package lab04;

public class Reader {
    private String name;
    private Book book;

    public Reader() {
        this.name = "";
        this.book = new Book();
    }

    public Reader(String name, Book book) {
        setName(name);
        setBook(book);
    }

    public Reader(Reader new_reader) {
        this.name = new_reader.name;
        this.book = new_reader.book;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setBook(Book book) {
        this.book = book;
    }

    public String getName() {
        return name;
    }
    public Book getBook() {
        return book;
    }

    public boolean returnedBook(String finish_month, int finish_day) {
        return book.checkReader(finish_month, finish_day);
    }
}
