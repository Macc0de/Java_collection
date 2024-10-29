package lab07;
import java.util.HashMap;

public class BestSubject {
    private HashMap<String, Integer> sum;
    private HashMap<String, Integer> count;

    public BestSubject() {
        sum = new HashMap<>();
        count = new HashMap<>();
    }

    public void add(SchoolBoy school_boy) {
        String subject = school_boy.getSubject();
        int grade = school_boy.getGrade();

        sum.put(subject, sum.getOrDefault(subject, 0) + grade);
        count.put(subject, count.getOrDefault(subject, 0) + 1);
    }

    public void find() {
        double highest_grade = 0.0, avg_grade;
        String best_subject = null;

        for(String subject : sum.keySet()) {
            avg_grade = (double) sum.get(subject) / count.get(subject);
            if(avg_grade > highest_grade) {
                highest_grade = avg_grade;
                best_subject = subject;
            }
        }
        System.out.printf("Предмет с лучшей средней успеваемостью(%.2f) - (%s)\n", highest_grade, best_subject);
    }
}
