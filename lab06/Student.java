package lab06;
import java.util.Comparator;

public class Student extends Studying {
    private int[] grades_coursework; // Оценки за курсовые

    public Student(String name, byte age) {
        super(name, age);
        try {
            if(age < 18 || age > 24) {
                throw new IllegalArgumentException("Возраст студента должен быть от 18 до 24!");
            }
        } catch (IllegalArgumentException e) {
            System.out.printf("Исключение: %s - %s\n", name, e.getMessage());
        }
    }

    public void setGradesCoursework(int[] grades) {
        try {
            if(grades == null) {
                throw new NullPointerException("Пустой массив оценок!");
            }
            if(!containCorrectGrades(grades)) {
                throw new IllegalArgumentException("Оценка за курсовую может быть только от 2 до 5!");
            }
            this.grades_coursework = grades;
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.printf("Исключение: %s - %s\n", getName(), e.getMessage());
        }
    }

    public boolean checkAvgCourseWork() { // Курсовые
        if(grades_coursework == null)
            return false;

        for(int i: grades_coursework) {
            if(i != 5) // Все ли равны 5?
                return false;
        }
        return true;
    }

    public boolean checkAvg() {
        return !(checkAvgValue() < 4.75);
    }

    @Override
    public boolean isScholarship() {
        return checkAvg() && checkAvgCourseWork();
    }
}

class StudentAvgValue implements Comparator<Student> {
    @Override
    public int compare(Student first, Student second) {
        return Double.compare(second.checkAvgValue(), first.checkAvgValue());
    }
}
