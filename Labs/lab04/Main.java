package lab04;
// Композиция
public class Main {
    public static void infoReader(Book book, Reader person, String return_month, int return_day) {
        if(return_day < 1 || return_day > 31)
            throw new IllegalArgumentException("Некорректное значение дня!");

        System.out.printf("(Срок - %d %s)\n", book.getFinishDay(), book.getFinishMonth());
        if(person.returnedBook(return_month, return_day))
            System.out.printf("%s сдал книгу вовремя - %d %s\n\n", person.getName(), return_day, return_month);
        else
            System.out.printf("%s сдал книгу не вовремя! - %d %s\n\n", person.getName(), return_day, return_month);
    }

    public static void main(String[] args) {
        String name1 = "Петров", name2 = "Васечкин",
                title1 = "Java 8. Полное руководство", title2 = "Крейцерова соната",
                author1 = "Г. Шилдт", author2 = "Л. Толстой",
                deadline_month1 = "April", deadline_month2 = "March",
                return_month = "March";
        int deadline_day1 = 1, deadline_day2 = 25, return_day = 29;

        Book book1 = new Book(title1, author1, deadline_month1, deadline_day1);
        Book book2 = new Book(title2, author2, deadline_month2, deadline_day2);

        Reader person1 = new Reader(name1, book1);
        Reader person2 = new Reader(name2, book2);

        infoReader(book1, person1, return_month, return_day);
        infoReader(book2, person2, return_month, return_day);
    }
}
