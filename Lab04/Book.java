package Lab04;

public class Book {
    private String title;
    private String author;
    private String finish_month;
    private int finish_day;
    private final String[] months = {"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};

    public Book() {
        this.title = "";
        this.author = "";
        this.finish_day = 0;
        this.finish_month = "";
    }

    public Book(String title, String author, String finish_month, int finish_day) {
        setTitle(title);
        setAuthor(author);
        setFinishMonth(finish_month);
        setFinishDay(finish_day);
    }

    public Book(Book new_book) {
        this.title = new_book.title;
        this.author = new_book.author;
        this.finish_month = new_book.finish_month;
        this.finish_day = new_book.finish_day;
    }

    // Индекс месяца ищет
    public int index_month(String month) {
        for(int i = 0; i < months.length; i++)
            if(months[i].equals(month))
                return i;
        return -1;
    }

    // Метод проверяющий сдал ли читатель вовремя?
    public boolean checkReader(String return_month, int return_day) {
        int current_month = index_month(return_month); // Когда сдали
        int deadline_month = index_month(this.finish_month); // Срок сдачи

        if(current_month == -1 || deadline_month == -1)
            throw new IllegalArgumentException("Такого месяца нет!");

        if(current_month > deadline_month) // Если месяцем позже
            return false;
        if((current_month == deadline_month) && (this.finish_day < return_day)) // Если ранее днем в этом месяце
            return false;

        return true;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setFinishMonth(String finish_month) {
        this.finish_month = finish_month;
    }
    public void setFinishDay(int finish_day) {
        if(finish_day < 1 || finish_day > 31)
            throw new IllegalArgumentException("Некорректное значение дня!");
        this.finish_day = finish_day;
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getFinishMonth() {
        return finish_month;
    }
    public int getFinishDay() {
        return finish_day;
    }
}
