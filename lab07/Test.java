package lab07;
import java.util.HashSet;

public class Test {
    public static void main(String[] args) {

        // (1) Множества
        // HashSet<>, TreeSet<>
        HashSet<String> cities = new HashSet<>();
        cities.add("Russia");
        cities.add("Bolton");
        cities.add("America");
        System.out.print(cities);

        /*TreeSet<String> cities = new TreeSet<>();
        cities.add("Odesa");
        cities.add("Kharkiv");
        cities.add("Poltava");
        System.out.print(cities);*/

        // сортирует по длине
        //TreeSet<String> cities = new TreeSet<>((s1, s2)-> Integer.compare(s1.length(), s2.length()));


        // (2) Ассоциативные массивы
        // HashMap<K,V>, TreeMap<K,V> - тип ключа, тип значения (Словари)
        /*HashMap<String, Integer> book = new HashMap<>(); // String, Integer
        book.put("1", 10);
        book.put("2", 20);
        book.put("3", 30); //book.get("3")

        for(Map.Entry entry : book.entrySet()) {

        }*/

    }

}
