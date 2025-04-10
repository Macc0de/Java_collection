package lab05;

public abstract class Studying {
    private String name;
    private char gender;
    private byte age;
    protected int[] grades;

    public Studying() {
        this.name = "";
        this.gender = ' ';
        this.age = 0;
        this.grades = new int[0];
    }

    public Studying(String name, char gender, byte age) {
        setName(name);
        setGender(gender);
        setAge(age);
    }

    public abstract boolean isScholarship();

    public void setName(String name) { this.name = name; }
    public void setGender(char gender) { this.gender = gender; }
    public void setAge(byte age) { this.age = age; }
    public void setGrades(int[] grades) {
        this.grades = grades;
    }

    public String getName() { return name; }
    public char getGender() { return gender; }
    public byte getAge() { return age; }
}
