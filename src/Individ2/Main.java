package Individ2;

import com.google.gson.*;
import java.io.*;
import java.util.*;

public class Main {
    // Метод для загрузки данных о странах и их континентах из JSON-файла
    private static void loadContinentData(TreeMap<String, String> countryToContinent) {
        String file_path = "continents.json";
        try (FileReader reader = new FileReader(file_path)) {
            JsonArray countries = JsonParser.parseReader(reader).getAsJsonArray();
            for (int i = 0; i < countries.size(); i++) {
                JsonObject countryObject = countries.get(i).getAsJsonObject();

                String countryName = countryObject.get("country").getAsString();
                String continent = countryObject.get("continent").getAsString();

                countryToContinent.put(countryName, continent);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке данных о странах: " + e.getMessage());
        } catch (JsonParseException e) {
            System.err.println("Ошибка разбора JSON: " + e.getMessage());
        }
    }

    private static String getContinent(String country, TreeMap<String, String> countryToContinent) {
        return countryToContinent.get(country);
    }

    public static void readFile(String file_path, TreeMap<String, ArrayList<Chocolate>> continent_chocolate) {
        TreeMap<String, String> countryToContinent = new TreeMap<>();
        loadContinentData(countryToContinent); // страна - континент

        int i = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file_path))) {
            String[] info = new String[8];
            String str;

            while ((str = reader.readLine()) != null) {
                info[i] = str;

                i++;
                if (i == 8) {
                    String country_exporter = info[0];
                    String company_exporter = info[1];
                    String brand = info[2];
                    int index_expertise = Integer.parseInt(info[3]);
                    int year_expertise = Integer.parseInt(info[4]);
                    int percent_cacao = Integer.parseInt(info[5].replace("%", ""));
                    String country_brand = info[6];
                    float grade = Float.parseFloat(info[7]);

                    String continent = getContinent(country_exporter, countryToContinent);

                    Chocolate chocolate = new Chocolate(country_exporter, company_exporter, brand, index_expertise, year_expertise,
                            percent_cacao, country_brand, grade);
                    continent_chocolate.computeIfAbsent(continent, key -> new ArrayList<>()).add(chocolate);

                    i = 0;
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    public static int countCountryExporters(TreeMap<String, ArrayList<Chocolate>> continent_chocolate) { // (1)
        int count_all_countries = 0;
        for (Map.Entry<String, ArrayList<Chocolate>> entry : continent_chocolate.entrySet()) {
            ArrayList<Chocolate> chocolates = entry.getValue();
            System.out.print(entry.getKey() + ": ");

            long count_countries = chocolates.stream().map(Chocolate::getCountryExporter).distinct().count();
            count_all_countries += (int)count_countries;
            System.out.print(count_countries + "\n");
        }
        return count_all_countries;
    }

    public static void histogramCountryExporters(TreeMap<String, ArrayList<Chocolate>> continent_chocolate, int count_all_countries) { // (2)
        for(Map.Entry<String, ArrayList<Chocolate>> entry : continent_chocolate.entrySet()) {
            String continent = entry.getKey();
            ArrayList<Chocolate> chocolates = entry.getValue();

            long count_countries = chocolates.stream().map(Chocolate::getCountryExporter).distinct().count();
            double percentage = (count_countries * 100.0) / count_all_countries;
            //int bar_length = (int)(percentage / 2);
            int bar_length = (int)(percentage * 1.3);

            String bar = "▃".repeat(bar_length / 3) + "▆".repeat(bar_length / 2) + "█".repeat(bar_length / 2);

            System.out.printf("%-15s%10.2f%%       %s\n", continent, percentage, bar);
        }
    }

    public static boolean topBestCountryExporters(int year, TreeMap<String, ArrayList<Chocolate>> continent_chocolate) { // (3)
        int count = 0, flag = 0;
        boolean check = false;
        double total_grade = 0.0d;
        for(Map.Entry<String, ArrayList<Chocolate>> entry : continent_chocolate.entrySet()) {
            String continent = entry.getKey();
            flag = 0;

            // среднего рейтинга по странам
            HashMap<String, Double> country_avgGrade = new HashMap<>(); // страна - сумма оценок
            HashMap<String, Integer> count_country = new HashMap<>(); // страна - кол-во

            // проверить все страны континента
            for (Chocolate chocolate : entry.getValue()) { // Массив шоколадок

                if (chocolate.getYearExpertise() == year) {
                    String country = chocolate.getCountryExporter();

                    // страны с одним названием - суммирует их оценки
                    country_avgGrade.merge(country, (double) chocolate.getGrade(), Double::sum);
                    // страны с одним названием - кол-во их оценок
                    count_country.merge(country, 1, Integer::sum);
                    flag = 1;
                    check = true;
                }
            }

            if (flag == 1) {
                // Рассчитываем средние оценки
                for (String country : country_avgGrade.keySet()) {
                    total_grade = country_avgGrade.get(country);
                    count = count_country.get(country);
                    country_avgGrade.put(country, total_grade / count);
                }

                // Сортируем по среднему рейтингу
                ArrayList<Map.Entry<String, Double>> sorted_countries = new ArrayList<>(country_avgGrade.entrySet());
                sorted_countries.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

                System.out.printf("(%s)\n", continent);
                int i = 0;
                for (Map.Entry<String, Double> country : sorted_countries) { // Страна - средняя оценка(стран много с одним названием)
                    if (i < 10)
                        System.out.printf("%s - %.2f\n", country.getKey(), country.getValue());
                    i++;
                }
                System.out.println();
            }
        }

        return check;
    }

    public static void createTable(TreeMap<String, ArrayList<Chocolate>> continent_chocolate) { // (4)
        TreeMap<String, Integer> count_brands = new TreeMap<>();
        HashMap<String, HashSet<String>> count_countryExporters = new HashMap<>();
        HashMap<String, Integer> min_content = new HashMap<>();
        HashMap<String, Integer> max_content = new HashMap<>();
        HashMap<String, Chocolate> best_brand = new HashMap<>();

        for(Map.Entry<String, ArrayList<Chocolate>> entry : continent_chocolate.entrySet()) { // континент - массив стран
            ArrayList<Chocolate> chocolates = entry.getValue();

            for(Chocolate chocolate : chocolates) {

                String country_brand = chocolate.getCountryBrand(); // страна выпускающая шоколад

                count_brands.put(country_brand, count_brands.getOrDefault(country_brand, 0) + 1);
                count_countryExporters.computeIfAbsent(country_brand, k -> new HashSet<>()).add(chocolate.getCountryExporter());
                min_content.put(country_brand, Math.min(min_content.getOrDefault(country_brand, chocolate.getPercentCacao()), chocolate.getPercentCacao()));
                max_content.put(country_brand, Math.max(max_content.getOrDefault(country_brand, chocolate.getPercentCacao()), chocolate.getPercentCacao()));

                best_brand.compute(country_brand, (first, second) -> {
                    if (second == null || chocolate.getGrade() > second.getGrade()) {
                        return chocolate;
                    }
                    return second;
                });
            }
        }

        String file = "table.txt";
        try(FileWriter writer = new FileWriter(file)) {
            writer.write("|кол-во марок шоколада | кол-во стран-экспортеров | минимальное и максимальное содержание какао | год экспертной оценки - лучшая марка шоколада|\n");
            for(String country_brand : count_brands.keySet()) {
                int countBrands = count_brands.get(country_brand);
                int countExporters = count_countryExporters.get(country_brand).size();
                int minContent = min_content.get(country_brand);
                int maxContent = max_content.get(country_brand);
                Chocolate bestBrand = best_brand.get(country_brand);

                String min = String.valueOf(minContent) + '%',
                        max = String.valueOf(maxContent) + '%';
                String formattedString = String.format("%-23s%-8d%-8d%-8s%-8s%-8d%s\n",
                        country_brand, countBrands, countExporters, min, max,
                        bestBrand.getYearExpertise(), bestBrand.getBrand());

                writer.write(formattedString);
            }
            System.out.printf("Файл создан - %s\n", file);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TreeMap<String, ArrayList<Chocolate>> continent_chocolate = new TreeMap<>();
        String file_path = "data_chocolate.txt";

        readFile(file_path, continent_chocolate);

        /*for (Map.Entry<String, ArrayList<Chocolate>> entry : continent_chocolate.entrySet()) {
            System.out.println(entry.getKey() + ":\n");
            for(Chocolate choc : entry.getValue()) {
                System.out.printf("%s - ", choc.getCountryExporter());
            }
            System.out.println("\n---------------------------");

        }*/

        System.out.println("(1) Кол-во стран-экспортеров какао-бобов в каждой части света:");
        int count_all_countries = countCountryExporters(continent_chocolate);

        System.out.println("\n(2) Гистограмма стран-экспортеров:");
        histogramCountryExporters(continent_chocolate, count_all_countries);

        System.out.println("\n(3) Топ-10 лучших стран-экспортеров:");
        int year = 2006;
        if(!topBestCountryExporters(year, continent_chocolate))
            System.out.println("Не найдено\n");

        System.out.println("(4) Таблица: ");
        createTable(continent_chocolate);
    }
}
