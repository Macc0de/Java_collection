package Individ2;

import com.google.gson.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void loadContinentData(TreeMap<String, String> countryContinent) {
        String filePath = "continents.json";
        try (FileReader reader = new FileReader(filePath)) {
            JsonArray countries = JsonParser.parseReader(reader).getAsJsonArray();
            for (int i = 0; i < countries.size(); i++) {
                JsonObject countryObject = countries.get(i).getAsJsonObject();

                String country = countryObject.get("country").getAsString();
                String continent = countryObject.get("continent").getAsString();

                countryContinent.put(country, continent);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке данных: " + e.getMessage());
        } catch (JsonParseException e) {
            System.err.println("Ошибка разбора JSON: " + e.getMessage());
        }
    }

    private static Chocolate getChocolate(String[] info) { // для readFile
        String countryExporter = info[0];
        String companyExporter = info[1];
        String brand = info[2];
        int indexExpertise = Integer.parseInt(info[3]);
        int yearExpertise = Integer.parseInt(info[4]);
        int percentCacao = Integer.parseInt(info[5].replace("%", ""));
        String countryBrand = info[6];
        float grade = Float.parseFloat(info[7]);

        return new Chocolate(countryExporter, companyExporter, brand, indexExpertise, yearExpertise,
                percentCacao, countryBrand, grade);
    }

    public static void readFile(String filePath, TreeMap<String, ArrayList<Chocolate>> continentChocolate) { // (0)
        TreeMap<String, String> countryContinent = new TreeMap<>();
        loadContinentData(countryContinent);

        int i = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[] info = new String[8];
            String str;

            while ((str = reader.readLine()) != null) {
                info[i] = str;
                i++;
                if (i == 8) {
                    Chocolate chocolate = getChocolate(info);

                    String continent = chocolate.getContinent(countryContinent);
                    continentChocolate.computeIfAbsent(continent, key -> new ArrayList<>()).add(chocolate);
                    i = 0;
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    public static int countCountryExporters(TreeMap<String, ArrayList<Chocolate>> continentChocolate) { // (1)
        int countAllCountries = 0;
        for (Map.Entry<String, ArrayList<Chocolate>> entry : continentChocolate.entrySet()) {
            ArrayList<Chocolate> chocolates = entry.getValue();
            System.out.print(entry.getKey() + ": ");

            long countCountries = chocolates.stream().map(Chocolate::getCountryExporter).distinct().count();
            countAllCountries += (int)countCountries;
            System.out.print(countCountries + "\n");
        }
        return countAllCountries;
    }

    public static void histogramCountryExporters(TreeMap<String, ArrayList<Chocolate>> continentChocolate, int countAllCountries) { // (2)
        for(Map.Entry<String, ArrayList<Chocolate>> entry : continentChocolate.entrySet()) {
            String continent = entry.getKey();
            ArrayList<Chocolate> chocolates = entry.getValue();

            long countCountries = chocolates.stream().map(Chocolate::getCountryExporter).distinct().count();
            double percentage = (countCountries * 100.0) / countAllCountries;

            int totalBarLength = 65;
            int barLength = (int)((percentage / 100) * totalBarLength);
            int last = totalBarLength - barLength;

            String bar = "█".repeat(barLength) + "░".repeat(last);

            System.out.printf("%-15s%10.2f%%       %s\n", continent, percentage, bar);
        }
    }

    public static boolean topBestCountryExporters(int year, TreeMap<String, ArrayList<Chocolate>> continentChocolate) { // (3)
        int count = 0, flag = 0;
        boolean check = false;
        double totalGrade = 0.0d;
        for(Map.Entry<String, ArrayList<Chocolate>> entry : continentChocolate.entrySet()) {
            String continent = entry.getKey();
            flag = 0;

            HashMap<String, Double> countryAvgGrade = new HashMap<>(); // страна - сумма оценок
            HashMap<String, Integer> countryCount = new HashMap<>(); // страна - кол-во

            for(Chocolate chocolate : entry.getValue()) {
                if(chocolate.getYearExpertise() == year) {
                    String country = chocolate.getCountryExporter();

                    // страны с одним названием - суммирует их оценки
                    countryAvgGrade.merge(country, (double) chocolate.getGrade(), Double::sum); // обновление значения
                    // страны с одним названием - кол-во их оценок
                    countryCount.merge(country, 1, Integer::sum);
                    flag = 1;
                    check = true;
                }
            }

            if (flag == 1) {
                for(String country : countryAvgGrade.keySet()) { // средняя оценка
                    totalGrade = countryAvgGrade.get(country);
                    count = countryCount.get(country);
                    countryAvgGrade.put(country, totalGrade / count);
                }

                // сортировка по среднему рейтингу
                ArrayList<Map.Entry<String, Double>> sortedCountries = new ArrayList<>(countryAvgGrade.entrySet());
                sortedCountries.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

                System.out.printf("(%s)\n", continent);
                int i = 0;
                for(Map.Entry<String, Double> country : sortedCountries) { // страна - средняя оценка
                    if (i < 10)
                        System.out.printf("%s - %.2f\n", country.getKey(), country.getValue());
                    i++;
                }
                System.out.println();
            }
        }
        return check;
    }

    public static void createTable(TreeMap<String, ArrayList<Chocolate>> continentChocolate) { // (4)
        TreeMap<String, Integer> countBrands = new TreeMap<>();
        HashMap<String, HashSet<String>> countCountryExporters = new HashMap<>();
        HashMap<String, Integer> minContent = new HashMap<>();
        HashMap<String, Integer> maxContent = new HashMap<>();
        HashMap<String, Chocolate> bestBrand = new HashMap<>();

        for(Map.Entry<String, ArrayList<Chocolate>> entry : continentChocolate.entrySet()) { // континент - массив стран
            ArrayList<Chocolate> chocolates = entry.getValue();
            for(Chocolate chocolate : chocolates) {
                String countryBrand = chocolate.getCountryBrand(); // страна, выпускающая шоколад

                countBrands.put(countryBrand, countBrands.getOrDefault(countryBrand, 0) + 1);
                countCountryExporters.computeIfAbsent(countryBrand, k -> new HashSet<>()).add(chocolate.getCountryExporter());
                minContent.put(countryBrand, Math.min(minContent.getOrDefault(countryBrand, chocolate.getPercentCacao()), chocolate.getPercentCacao()));
                maxContent.put(countryBrand, Math.max(maxContent.getOrDefault(countryBrand, chocolate.getPercentCacao()), chocolate.getPercentCacao()));

                bestBrand.compute(countryBrand, (first, second) -> {
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
            for(String countryBrand : countBrands.keySet()) {
                int count = countBrands.get(countryBrand);
                int countExporters = countCountryExporters.get(countryBrand).size();
                int min = minContent.get(countryBrand);
                int max = maxContent.get(countryBrand);
                Chocolate best = bestBrand.get(countryBrand);

                String minFormat = String.valueOf(min) + '%',
                        maxFormat = String.valueOf(max) + '%';
                String formattedString = String.format("%-23s%-8d%-8d%-8s%-8s%-8d%s\n",
                        countryBrand, count, countExporters, minFormat, maxFormat,
                        best.getYearExpertise(), best.getBrand());

                writer.write(formattedString);
            }
            System.out.printf("Файл создан - %s\n", file);
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        TreeMap<String, ArrayList<Chocolate>> continentChocolate = new TreeMap<>();
        String filePath = "data_chocolate.txt";

        readFile(filePath, continentChocolate);

        System.out.println("(1) Кол-во стран-экспортеров какао-бобов в каждой части света:");
        int countAllCountries = countCountryExporters(continentChocolate);

        System.out.println("\n(2) Гистограмма стран-экспортеров:");
        histogramCountryExporters(continentChocolate, countAllCountries);

        System.out.println("\n(3) Топ-10 лучших стран-экспортеров:");
        int year = 2006;
        if(!topBestCountryExporters(year, continentChocolate))
            System.out.println("Не найдено для " + year + "\n");

        System.out.println("(4) Таблица: ");
        createTable(continentChocolate);
    }
}
