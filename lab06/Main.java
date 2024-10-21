package lab06;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void printScholars(ArrayList<SchoolBoy> schoolboys, ArrayList<Student> students) { // (1)
        ArrayList<Studying> scholars = new ArrayList<>();

        for(SchoolBoy schoolboy : schoolboys) {
            if(schoolboy.isScholarship()) { // Если есть стипендия
                scholars.add(schoolboy);
            }
        }

        for(Student student : students) {
            if(student.isScholarship()) {
                scholars.add(student);
            }
        }

        if(scholars.isEmpty()) {
            System.out.println("\nНет учащихся со стипендией!");
            return;
        }

        System.out.println("\n(1) Школьники и Студенты со стипендией, отсортированные по фамилиям:");
        Collections.sort(scholars);

        for(Studying person : scholars) {
            System.out.println(person.getName());
        }
    }

    public static SchoolBoy bestSchoolBoy(ArrayList<SchoolBoy> schoolboys) { // (2)
        return Collections.max(schoolboys, new AvgValue());
    }
    public static  Student bestStudent(ArrayList<Student> students) {
        return Collections.max(students, new AvgValue());
    }

    public static void sortSchool(ArrayList<SchoolBoy> schoolboys) { // (3)
        //Collections.sort(schoolboys, new SchoolAvgValue());
        //schoolboys.sort(new SchoolAvgValue());
        /*System.out.println("\n(3)\nШкольники, отсортированные по рейтингу успеваемости:");
        for(SchoolBoy schoolboy : schoolboys) {
            System.out.printf("%s - %.2f\n", schoolboy.getName(), schoolboy.checkAvgValue());
        }*/

        // Двойная сортировка
        schoolboys.sort(new CompareSchool());

        //Collections.sort(schoolboys, new NumberSchool());
        //schoolboys.sort(new NumberSchool());
        /*System.out.println("\nШкольники, отсортированные по номеру школы:");
        for(SchoolBoy schoolboy : schoolboys) {
            System.out.printf("%s - %d школа\n", schoolboy.getName(), schoolboy.getNumberSchool());
        }*/
        System.out.println("\n(3)\nШкольники, отсортированные по успеваемости, иначе по номеру школы:");
        for(SchoolBoy schoolboy : schoolboys) {
            System.out.printf("%s - (%.2f) - %d школа\n", schoolboy.getName(), schoolboy.checkAvgValue(), schoolboy.getNumberSchool());
        }
    }
    public static void sortStudent(ArrayList<Student> students) {
        students.sort(new StudentAvgValue());

        System.out.println("\nСтуденты, отсортированные по рейтингу успеваемости:");
        for(Student student : students) {
            System.out.printf("%s - %.2f\n", student.getName(), student.checkAvgValue());
        }
    }

    public static void fillOlympiad(SchoolBoy schoolboy, int oblast, int school, int city) {
        schoolboy.setOlympiadOblast(oblast);
        schoolboy.setOlympiadSchool(school);
        schoolboy.setOlympiadCity(city);
    }

    public static void addGrades(Studying man1, Studying man2, Studying man3, int[] grades1, int[] grades2, int[] grades3) {
        man1.setGrades(grades1);
        man2.setGrades(grades2);
        man3.setGrades(grades3);
    }

    public static void addGradesCourseWork(Student stud1, Student stud2, Student stud3, int[] grades1, int[] grades2, int[] grades3) {
        stud1.setGradesCoursework(grades1);
        stud2.setGradesCoursework(grades2);
        stud3.setGradesCoursework(grades3);
    }

    public static void main(String[] args) {
        String name1 = "Яна", name2 = "Олег", name3 = "Максим", name4 = "Александр", name5 = "Никита", name6 = "Денис";
        byte age1 = 16, age2 = 13, age3 = 15, age4 = 22, age5 = 19, age6 = 20,
                num_school1 = 38, num_school2 = 12, num_school3 = 18;

        SchoolBoy schoolboy1 = new SchoolBoy(name1, age1, num_school1);
        SchoolBoy schoolboy2 = new SchoolBoy(name2, age2, num_school2);
        SchoolBoy schoolboy3 = new SchoolBoy(name3, age3, num_school3);

        int[] schoolboy_grades1 = {5, 5, 5, 5, 4, 4}; // матем, рус, история, англ | биология, география
        int[] schoolboy_grades2 =  {5, 5, 5, 5, 5, 4};
        int[] schoolboy_grades3 =  {5, 5, 5, 5, 4, 4};

        addGrades(schoolboy1, schoolboy2, schoolboy3, schoolboy_grades1, schoolboy_grades2, schoolboy_grades3);

        fillOlympiad(schoolboy1, 1, 1, 1); // oblast, school, city
        fillOlympiad(schoolboy2, 1, 0, 0);
        fillOlympiad(schoolboy3, 0, 0, 3);

        Student student1 = new Student(name4, age4);
        Student student2 = new Student(name5, age5);
        Student student3 = new Student(name6, age6);

        int[] student_grades1 = {5, 4, 5, 5};
        int[] student_grades2 = {5, 5, 5, 5};
        int[] student_grades3 = {5, 5, 2, 3};
        int[] student_coursework1 = {5, 5};
        int[] student_coursework2 = {5, 2};
        int[] student_coursework3 = {5, 5};

        addGrades(student1, student2, student3, student_grades1, student_grades2, student_grades3);
        addGradesCourseWork(student1, student2, student3, student_coursework1, student_coursework2, student_coursework3);

        // Массивы
        ArrayList<SchoolBoy> schoolboys = new ArrayList<>();
        schoolboys.add(schoolboy1);
        schoolboys.add(schoolboy2);
        schoolboys.add(schoolboy3);

        ArrayList<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);

        // (1) Студент (все курсовые на 5 && средний балл выше 4,75)
        // Школьник (главные предметы на 5, остальные >= 4 &&
        // участие в областной || первое в школьной || призовое в городской)
        printScholars(schoolboys, students);

        // (2)
        System.out.print("\n(2)\n");
        SchoolBoy best_schoolboy = bestSchoolBoy(schoolboys);
        if(bestSchoolBoy(schoolboys) != null)
            System.out.printf("Лучший школьник - %s (%.2f)\n", best_schoolboy.getName(), best_schoolboy.checkAvgValue());
        Student best_stud = bestStudent(students);
        if(bestStudent(students) != null)
            System.out.printf("Лучший студент - %s (%.2f)\n", best_stud.getName(), best_stud.checkAvgValue());

        // (3)
        sortSchool(schoolboys);
        sortStudent(students);
    }
}
