package Individ2;

import com.google.gson.*;
import java.io.*;
import java.net.HttpURLConnection; // для отправки HTTP-запросов
import java.net.URL;

public class create_json {
    public static void loadCountries(String filePath) {
        try {
            URL url = new URL("https://restcountries.com/v3.1/all");
            // создает соединение с URL, и результат приводится к HttpURLConnection, чтобы работать с HTTP-протоколом
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // устанавливается метод HTTP-запроса GET, который используется для получения данных.
            connection.setRequestMethod("GET");
            connection.connect();

            int response = connection.getResponseCode();
            if (response != 200) {
                System.err.println("Ошибка подключения к API: " + response);
                return;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();
            //System.out.println(json_builder.toString()); // вывод

            JsonArray countries = JsonParser.parseString(jsonBuilder.toString()).getAsJsonArray();
            JsonArray countryContinent = new JsonArray();

            for (int i = 0; i < countries.size(); i++) { // Парсинг
                JsonObject country = countries.get(i).getAsJsonObject();
                String countryName = country.get("name").getAsJsonObject().get("common").getAsString();
                String continent = country.get("continents").getAsJsonArray().get(0).getAsString();

                JsonObject info = new JsonObject();
                info.addProperty("country", countryName);
                info.addProperty("continent", continent);

                countryContinent.add(info);
            }

            // Сохранение в json файл
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter file = new FileWriter(filePath)) {
                gson.toJson(countryContinent, file);
            }
            connection.disconnect();
        } catch (Exception e) {
            System.err.println("Ошибка при загрузке данных: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        loadCountries("continent.json");
    }
}
