package lab07;
import java.util.*;
import java.io.*;

public class SchoolBoy {
    private String surname;
    private String name;
    private byte num_class;
    private String subject;
    private byte grade;

    public SchoolBoy() {
        this.surname = "";
        this.name = "";
        this.num_class = 0;
        this.subject = "";
        this.grade = 0;
    }

    public SchoolBoy(String surname, String name, byte num_class, String subject, byte grade) {
        setSurname(surname);
        setName(name);
        setNumClass(num_class);
        setSubject(subject);
        setGrade(grade);
    }

    public boolean equal(String subject) {
        return getSubject().equals(subject);
    }
    public boolean equal(byte grade) {
        return getGrade() == grade;
    }
    public boolean equal(String surname, String name) { return (getSurname().equals(surname) && getName().equals(name)); }

    public void print() {
        System.out.printf("%s %s, ", getSurname(), getName());
    }

    public static void readFile(String file_name, TreeMap<Byte, ArrayList<SchoolBoy>> journal) {
        try(BufferedReader reader = new BufferedReader(new FileReader(file_name))) { // чтение построчно
            String str;
            while((str = reader.readLine()) != null) {
                String[] word = str.split("\\s+");

                String surname = word[0];
                String name = word[1];
                byte num_class = Byte.parseByte(word[2]);
                String subject = word[3];
                byte grade = Byte.parseByte(word[4]);

                SchoolBoy school_boy = new SchoolBoy(surname, name, num_class, subject, grade);

                journal.computeIfAbsent(num_class, key -> new ArrayList<>()).add(school_boy);
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    public static void writeFile(ArrayList<SchoolBoy> array_school, byte num_class) {
        try(FileWriter writer = new FileWriter("class" + num_class + ".txt")) {
            for(SchoolBoy school_boy : array_school)
                writer.write(school_boy.getSurname() + " " + school_boy.getName() + "\n");
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    public static void writeFile2(TreeSet<String> subjects, ArrayList<SchoolBoy> array_school, byte num_class) {
        try(FileWriter writer = new FileWriter("Your class" + num_class + ".txt")) {
            for(String subject : subjects) { // Список предметов
                writer.write(subject + ":\n");
                for(SchoolBoy school_boy : array_school) {
                    if(school_boy.equal(subject))
                        writer.write(school_boy.getSurname() + " " + school_boy.getName() + " " + school_boy.getGrade() + "\n");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    public void setSurname(String surname) { this.surname = surname; }
    public void setName(String name) { this.name = name; }
    public void setNumClass(byte num_class) { this.num_class = num_class; }
    public void setSubject(String subject) { this.subject = subject; }
    public void setGrade(byte grade) { this.grade = grade; }

    public String getSurname() { return surname; }
    public String getName() { return name; }
    public byte getNumClass() { return num_class; }
    public String getSubject() { return subject; }
    public byte getGrade() { return grade; }
}
