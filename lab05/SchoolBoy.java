package lab05;

public class SchoolBoy extends Studying {
    private int olympiad_oblast;
    private int olympiad_school;
    private int olympiad_city;

    public SchoolBoy(String name, char gender, byte age) {
        super(name, gender, age);
    }

    public void setOlympiadOblast(int olympiad_oblast) { this.olympiad_oblast = olympiad_oblast; }
    public void setOlympiadSchool(int olympiad_school) { this.olympiad_school = olympiad_school; }
    public void setOlympiadCity(int olympiad_city) { this.olympiad_city = olympiad_city; }

    public boolean isFirst() { // (1) Девочки
        return (this.getGender() == 'ж') && (olympiad_oblast == 1 || olympiad_school == 1 || olympiad_city == 1);
    }

    public boolean checkOlympiad() {
        if(olympiad_oblast > 0)
            return true;
        if(olympiad_school == 1)
            return true;
        if(olympiad_city <= 3 && olympiad_city > 0)
            return true;

        return false;
    }
    public boolean checkFive() {
        if(grades == null) // Если нет массива
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
        //if(checkFive() && checkOlympiad())
            //System.out.printf("Школьник %s", this.getName());
        return checkFive() && checkOlympiad();
    }
}
