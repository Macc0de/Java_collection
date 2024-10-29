package lab07;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

public class MiddlePerformance {
    private ArrayList<Map.Entry<Byte, Double>> average_grade;

    public MiddlePerformance() {
        average_grade = new ArrayList<>();
    }

    public void add(Byte key, double avg_grade) {
        average_grade.add(new AbstractMap.SimpleEntry<>(key, avg_grade)); // В массив <Класс, среднее значение>
    }

    public void sort_print() {
        average_grade.sort((a,b) -> Double.compare(b.getValue(), a.getValue()));

        for(Map.Entry<Byte, Double> entry : average_grade)
            System.out.printf("%d класс - %.2f\n", entry.getKey(), entry.getValue());
    }
}
