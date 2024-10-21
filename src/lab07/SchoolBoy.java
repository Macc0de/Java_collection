package lab07;

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
