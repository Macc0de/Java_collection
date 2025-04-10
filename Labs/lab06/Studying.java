package lab06;
import java.util.Comparator;

public abstract class Studying implements Comparable<Studying> {
    private String name;
    private byte age;
    protected int[] grades;

    public Studying() {
        this.name = "";
        this.age = 0;
        this.grades = new int[0];
    }

    public Studying(String name, byte age) {
        setName(name);
        setAge(age);
    }

    abstract boolean isScholarship();

    public double checkAvgValue() { // Средняя оценка за экзамены/предметы
        if(grades == null) // Если нет массива
            return -1;

        int sum = 0;
        for(int i: grades) {
            sum += i;
        }
        //System.out.printf("%s - (%f)", this.getName(), avg_grade);

        return (double) sum / grades.length;
    }

    @Override // Сортировка по имени
    public int compareTo(Studying person) {
        return name.compareTo(person.getName());
    }

    public static boolean startsWithUpperCase(String name) {
        char letter = name.charAt(0);
        return Character.isUpperCase(letter);
    }
    public static boolean containCorrectGrades(int[] grades) {
        for(int grade: grades) {
            if(grade < 2 || grade > 5)
                return false;
        }
        return true;
    }

    public void setName(String name) {
        try {
            if(name.isEmpty()) {
                throw new NullPointerException("Пустая строка!");
            }
            if(!startsWithUpperCase(name)) {
                throw new IllegalArgumentException("Имя должно начинаться с большой буквы!");
            }
            this.name = name;
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.printf("Исключение: %s - %s\n", name, e.getMessage());
        }
    }
    public void setAge(byte age) { this.age = age; }
    public void setGrades(int[] grades) {
        try {
            if(grades == null) {
                throw new NullPointerException("Пустой массив оценок!");
            }
            if(!containCorrectGrades(grades)) {
                throw new IllegalArgumentException("Оценка может быть только от 2 до 5!");
            }
            this.grades = grades;
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.printf("Исключение: %s - %s\n", name, e.getMessage());
        }
    }

    public String getName() { return name; }
    public byte getAge() { return age; }
    public int[] getGrades() { return grades; }
}

class AvgValue implements Comparator<Studying> { //
    @Override
    public int compare(Studying first, Studying second) {
        return Double.compare(first.checkAvgValue(), second.checkAvgValue());
    }
}
