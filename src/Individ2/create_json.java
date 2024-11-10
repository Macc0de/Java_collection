package Individ2;

import com.google.gson.*;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class create_json {
    public static void main(String[] args) {
        saveCountryDataToFile("continents.json");
    }

    public static void saveCountryDataToFile(String filePath) {
        try {
            System.out.println("Подключение к API...");
            URL url = new URL("https://restcountries.com/v3.1/all");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                System.err.println("Ошибка подключения к API: " + responseCode);
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();
            System.out.println("Данные получены. Парсинг JSON...");

            JsonArray countries = JsonParser.parseString(jsonBuilder.toString()).getAsJsonArray();
            JsonArray countryContinentArray = new JsonArray();

            for (int i = 0; i < countries.size(); i++) {
                JsonObject country = countries.get(i).getAsJsonObject();
                String countryName = country.get("name").getAsJsonObject().get("common").getAsString();
                String continent = country.get("continents").getAsJsonArray().get(0).getAsString();

                JsonObject countryContinent = new JsonObject();
                countryContinent.addProperty("country", countryName);
                countryContinent.addProperty("continent", continent);

                countryContinentArray.add(countryContinent);
            }

            // Настраиваем Gson для форматированной записи
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter file = new FileWriter(filePath)) {
                gson.toJson(countryContinentArray, file);
                System.out.println("Данные успешно сохранены в файл " + filePath);
            }

            connection.disconnect();

        } catch (Exception e) {
            System.err.println("Ошибка при загрузке данных о странах: " + e.getMessage());
        }
    }
}
