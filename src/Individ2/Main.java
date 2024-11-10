package Individ2;

import com.google.gson.*;
import java.io.*;
import java.util.*;

public class Main {
    // Метод для загрузки данных о странах и их континентах из JSON-файла
    private static void loadContinentData(TreeMap<String, String> countryToContinent) { // отсюда "continents.json"
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

    // Метод для определения части света по стране (на английском языке)
    private static String getContinent(String country, TreeMap<String, String> countryToContinent) {
        return countryToContinent.get(country); // Из базы стран дает значение страны - континент
        //return countryToContinent.getOrDefault(country, "Неизвестный континент");
    }

    public static void main(String[] args) {
        String file_path = "data_chocolate.txt"; // Путь к файлу данных о какао-бобах

        TreeMap<String, String> countryToContinent = new TreeMap<>(); // страна - континент JSON
        loadContinentData(countryToContinent); // Загружаем данные из JSON-файла с континентами

        TreeMap<String, ArrayList<Chocolate>> continent_chocolate = new TreeMap<>(); // континент - массив шоколадок

        // Чтобы страны без повторов - посчитать их кол-во
        TreeMap<String, HashSet<String>> continent_country = new TreeMap<>(); // континент - массив стран
        int count_all_countries = 0, count_countries, i = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader(file_path))) {
            String str;

            String country_exporter = "";
            String company_exporter = "";
            String brand = "";
            int index_expertise = 0;
            int year_expertise = 0;
            int percent_cacao = 0;
            String country_brand = "";
            float grade = 0.0f;

            while((str = reader.readLine()) != null) {
                if(i == 0)
                    country_exporter = str;
                else if(i == 1)
                    company_exporter = str;
                else if(i == 2)
                    brand = str;
                else if(i == 3)
                    index_expertise = Integer.parseInt(str);
                else if(i == 4)
                    year_expertise = Integer.parseInt(str);
                else if(i == 5)
                    percent_cacao = Integer.parseInt(str.replace("%", ""));
                else if(i == 6)
                    country_brand = str;
                else if(i == 7)
                    grade = Float.parseFloat(str);

                i++;
                // Каждые 8 строк
                if(i == 8) {
                    String continent = getContinent(country_exporter, countryToContinent); // Континент для страны
                    //if(continent == null)
                    continent_country.computeIfAbsent(continent, key -> new HashSet<>()).add(country_exporter);

                    ///////////////////
                    Chocolate chocolate = new Chocolate(country_exporter, company_exporter, brand, index_expertise,
                            year_expertise, percent_cacao, country_brand, grade);
                    continent_chocolate.computeIfAbsent(continent, key -> new ArrayList<>()).add(chocolate);

                    i = 0;
                }

            }

            System.out.println("(1) Кол-во стран-экспортеров какао-бобов в каждой части света:");
            for (Map.Entry<String, HashSet<String>> entry : continent_country.entrySet()) {

                System.out.println(entry.getKey() + ": ");
                for(String elem: entry.getValue())
                    System.out.print(elem + " ");

                count_countries = entry.getValue().size();
                count_all_countries += count_countries;
                System.out.printf("\n%d\n\n", count_countries);
            }

        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

        /*try (BufferedReader reader = new BufferedReader(new FileReader(file_path))) {
            String[] fields = new String[8];  // массив для хранения значений строк

            String str;
            while ((str = reader.readLine()) != null) {
                fields[i] = str;

                i++;
                if (i == 8) {  // каждые 8 строк
                    String country_exporter = fields[0];
                    String company_exporter = fields[1];
                    String brand = fields[2];
                    int index_expertise = Integer.parseInt(fields[3]);
                    int year_expertise = Integer.parseInt(fields[4]);
                    int percent_cacao = Integer.parseInt(fields[5].replace("%", ""));
                    String country_brand = fields[6];
                    float grade = Float.parseFloat(fields[7]);

                    String continent = getContinent(country_exporter, countryToContinent);
                    continent_country.computeIfAbsent(continent, key -> new HashSet<>()).add(country_exporter);

                    Chocolate chocolate = new Chocolate(country_exporter, company_exporter, brand, index_expertise, year_expertise,
                            percent_cacao, country_brand, grade);
                    continent_chocolate.computeIfAbsent(continent, key -> new ArrayList<>()).add(chocolate);

                    i = 0;
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }*/

        /*for (Map.Entry<String, ArrayList<Chocolate>> entry : continent_chocolate.entrySet()) {
            System.out.println(entry.getKey() + ":\n");
            for(Chocolate choc : entry.getValue()) {
                System.out.printf("%s - ", choc.getCountryExporter());
            }
            System.out.println("\n---------------------------");

        }*/

        System.out.println("(2) Гистограмма стран-экспортеров по частям света:");
        for (Map.Entry<String, HashSet<String>> entry : continent_country.entrySet()) {
            String continent = entry.getKey();

            count_countries = entry.getValue().size();
            double percentage = (count_countries * 100.0) / count_all_countries;
            int barLength = (int)(percentage / 2);

            // Используем градацию для визуального эффекта
            String bar = "▃".repeat(barLength / 3) + "▆".repeat(barLength / 2) + "█".repeat(barLength / 2);

            System.out.printf("%-12s    %8.2f%%       %s\n", continent, percentage, bar);
        }

        System.out.println("\n(3)Топ-10 лучших стран-экспортеров:");

        int year = 2015, flag = 0, count = 0;
        double total_grade = 0.0d;
        for (Map.Entry<String, ArrayList<Chocolate>> entry : continent_chocolate.entrySet()) {
            String continent = entry.getKey();
            ArrayList<Chocolate> chocolates = entry.getValue();

            // среднего рейтинга по странам
            HashMap<String, Double> countryAverageGrade = new HashMap<>(); // страна - сумма оценок
            HashMap<String, Integer> countryCount = new HashMap<>(); // страна - кол-во

            // проверить все страны континента
            for(Chocolate chocolate: entry.getValue()) { // Массив шоколадок

                if (chocolate.getYearExpertise() == year) {  // Фильтр по году
                    String country = chocolate.getCountryExporter();

                    // страны с одним названием - суммирует их оценки
                    countryAverageGrade.merge(country, (double) chocolate.getGrade(), Double::sum);
                    // страны с одним названием - кол-во их оценок
                    countryCount.merge(country, 1, Integer::sum);
                }
            }

            // Рассчитываем средние оценки
            for (String country : countryAverageGrade.keySet()) {
                total_grade = countryAverageGrade.get(country);
                count = countryCount.get(country);
                countryAverageGrade.put(country, total_grade / count);
            }

            // Сортируем по среднему рейтингу
            ArrayList<Map.Entry<String, Double>> sortedCountries = new ArrayList<>(countryAverageGrade.entrySet());

            sortedCountries.sort((a, b) -> Double.compare(b.getValue(), a.getValue()));

            System.out.printf("(%s)\n", continent);
            i = 0;
            for(Map.Entry<String, Double> country : sortedCountries) { // Страна - средняя оценка(стран много с одним названием)
                if(i < 10)
                    System.out.printf("%s - %.2f\n", country.getKey(), country.getValue());
                i++;
            }
            System.out.println();
        }

        System.out.printf("\n(4) Таблица:\n");

        TreeMap<String, Integer> countryBrands = new TreeMap<>();
        HashMap<String, HashSet<String>> countryExporters = new HashMap<>();

        HashMap<String, Integer> minCacaoContent = new HashMap<>();
        HashMap<String, Integer> maxCacaoContent = new HashMap<>();
        HashMap<String, Chocolate> bestBrand = new HashMap<>();

        System.out.println("кол-во марок шоколада - кол-во стран-экспортеров");
        System.out.println("минимальное и максимальное процентное содержание какао");
        System.out.println("год соответствующей экспертной оценки - название лучшей марки шоколада\n");

        for (Map.Entry<String, ArrayList<Chocolate>> entry : continent_chocolate.entrySet()) { // континент - массив стран
            ArrayList<Chocolate> chocolates = entry.getValue();

            for (Chocolate chocolate : chocolates) {

                String country = chocolate.getCountryBrand(); // страна выпускающая шоколад

                countryBrands.put(country, countryBrands.getOrDefault(country, 0) + 1);
                countryExporters.computeIfAbsent(country, k -> new HashSet<>()).add(chocolate.getCountryExporter());
                minCacaoContent.put(country, Math.min(minCacaoContent.getOrDefault(country, chocolate.getPercentCacao()), chocolate.getPercentCacao()));
                maxCacaoContent.put(country, Math.max(maxCacaoContent.getOrDefault(country, chocolate.getPercentCacao()), chocolate.getPercentCacao()));

                bestBrand.compute(country, (first, second) -> {
                    if (second == null || chocolate.getGrade() > second.getGrade()) {
                        return chocolate;
                    }
                    return second;
                });
            }
        }

        try(FileWriter writer = new FileWriter("(4).txt")) {
            for (String country_brand : countryBrands.keySet()) {
                int brandCount = countryBrands.get(country_brand);
                int exporterCount = countryExporters.get(country_brand).size();
                int minCacao = minCacaoContent.get(country_brand);
                int maxCacao = maxCacaoContent.get(country_brand);
                Chocolate bestChocolate = bestBrand.get(country_brand);

                String formattedString = String.format("%s\n%d - %d\n%d - %d\n%d - %s\n\n",
                        country_brand, brandCount, exporterCount, minCacao, maxCacao,
                        bestChocolate.getYearExpertise(), bestChocolate.getBrand());

                writer.write(formattedString);
                System.out.printf("%s\n%d - %d\n%d - %d\n%d - %s\n\n", country_brand, brandCount, exporterCount,
                        minCacao, maxCacao, bestChocolate.getYearExpertise(), bestChocolate.getBrand());
            }
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}

// выделить страны, выпускающие шоколад и посчитать кол-во марок которые есть в этой стране
//long count_brands = chocolates.stream().map(Chocolate::getBrand); //.distinct().count();
// Кол-во марок шоколада в стране, которая выпускает шоколад

// Страна, выпускающая шоколад - список брендов шоколада