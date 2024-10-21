package Lab05;

public class Student extends Studying {
    private int[] grades_coursework; // Оценки за курсовые

    public Student(String name, char gender, byte age) {
        super(name, gender, age); // Вызов конструктора родителя
    }

    public void setGradesCoursework(int[] grades) { this.grades_coursework = grades; }

    public boolean isCourseWork() { // (2) Есть оценки за курсовые работы?
        for(int i: grades_coursework) {
            if(i > 0)
                return true;
        }
        return false;
    }

    public boolean checkAvgValue() { // Средняя оценка за экзамены
        if(grades == null) // Если нет массива
            return false;

        int sum = 0;
        for(int i: grades) {
            sum += i;
        }
        double avg_grade = (double) sum / grades.length;
        //System.out.printf("%s - (%f)", this.getName(), avg_grade);

        if(avg_grade < 4.75)
            return false;
        return true;
    }
    public boolean checkAvgCourseWork() { // Курсовые
        if(grades_coursework == null) // Если нет массива
            return false;

        for(int i: grades_coursework) {
            if(i != 5) // Все ли равны 5?
                return false;
        }
        return true;
    }

    @Override // аннотация
    public boolean isScholarship() {
        //if(checkAvgValue() && checkAvgCourseWork())
            //System.out.printf("Студент %s", this.getName());
        return checkAvgValue() && checkAvgCourseWork();
    }
}
