package lab06;
import java.util.Comparator;

public class SchoolBoy extends Studying {
    private byte number_school;
    private int olympiad_oblast;
    private int olympiad_school;
    private int olympiad_city;

    public SchoolBoy(String name, byte age, byte number_school) {
        super(name, age);
        try {
            if(age < 8 || age > 18) {
                throw new IllegalArgumentException("Возраст школьника должен быть от 8 до 18!");
            }
        } catch (IllegalArgumentException e) {
            System.out.printf("Исключение: %s - %s\n", name, e.getMessage());
        }
        setNumberSchool(number_school);
    }

    public void setOlympiadOblast(int olympiad_oblast) {
        try {
            if(olympiad_oblast < 0 || olympiad_oblast > 10) {
                throw new IllegalArgumentException("Место на областной олимпиаде может быть только от 0 до 10!");
            }
            this.olympiad_oblast = olympiad_oblast;
        } catch (IllegalArgumentException e) {
            System.out.printf("Исключение: %s - %s\n", getName(), e.getMessage());
        }
    }
    public void setOlympiadSchool(int olympiad_school) {
        try {
            if(olympiad_school < 0 || olympiad_school > 10) {
                throw new IllegalArgumentException("Место на школьной олимпиаде может быть только от 0 до 10!");
            }
            this.olympiad_school = olympiad_school;
        } catch (IllegalArgumentException e) {
            System.out.printf("Исключение: %s - %s\n", getName(), e.getMessage());
        }
    }
    public void setOlympiadCity(int olympiad_city) {
        try {
            if(olympiad_city < 0 || olympiad_city > 10) {
                throw new IllegalArgumentException("Место на городской олимпиаде может быть только от 0 до 10!");
            }
            this.olympiad_city = olympiad_city;
        } catch (IllegalArgumentException e) {
            System.out.printf("Исключение: %s - %s\n", getName(), e.getMessage());
        }
    }
    public void setNumberSchool(byte number_school) {
        try {
            if(number_school < 0) {
                throw new IllegalArgumentException("Номер школы должен быть положительным!");
            }
            this.number_school = number_school;
        } catch (IllegalArgumentException e) {
            System.out.printf("Исключение: %s - %s\n", getName(), e.getMessage());
        }
    }

    public byte getNumberSchool() { return number_school; }

    public boolean checkOlympiad() {
        if(olympiad_oblast > 0)
            return true;
        if(olympiad_school == 1)
            return true;
        return olympiad_city <= 3 && olympiad_city > 0;
    }
    public boolean checkFive() {
        if(grades == null)
            return false;

        for(int i = 0; i < grades.length; i++) {
            if(i < 4) {
                if (grades[i] != 5) // Проверка 4х предметов
                    return false;
            }
            else {
                if (grades[i] < 4) // Проверка остальных
                    return false;
            }
        }
        return true;
    }

    @Override
    public boolean isScholarship() {
        return checkFive() && checkOlympiad();
    }
}

class CompareSchool implements Comparator<SchoolBoy> {
    @Override
    public int compare(SchoolBoy first, SchoolBoy second) {
        int result = Double.compare(second.checkAvgValue(), first.checkAvgValue());
        if (result == 0) // Если объекты равны, то сортирует по номеру школы
            return Byte.compare(first.getNumberSchool(), second.getNumberSchool());

        return result;
    }
}
/*class NumberSchool implements Comparator<SchoolBoy> {
    @Override
    public int compare(SchoolBoy first, SchoolBoy second) {
        return Byte.compare(first.getNumberSchool(),second.getNumberSchool());
    }
}*/

