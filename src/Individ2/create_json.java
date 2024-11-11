package Individ2;

import com.google.gson.*;
import java.io.*;
import java.net.HttpURLConnection; // для отправки HTTP-запросов
import java.net.URL;

public class create_json {
    public static void load_countries(String file_path) {
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
            StringBuilder json_builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                json_builder.append(line);
            }
            reader.close();
            //System.out.println(json_builder.toString());

            JsonArray countries = JsonParser.parseString(json_builder.toString()).getAsJsonArray();
            JsonArray country_continent = new JsonArray();

            for (int i = 0; i < countries.size(); i++) { // Парсинг
                JsonObject country = countries.get(i).getAsJsonObject();
                String country_name = country.get("name").getAsJsonObject().get("common").getAsString();
                String continent = country.get("continents").getAsJsonArray().get(0).getAsString();

                JsonObject info = new JsonObject();
                info.addProperty("country", country_name);
                info.addProperty("continent", continent);

                country_continent.add(info);
            }

            // Сохранение в json файл
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter file = new FileWriter(file_path)) {
                gson.toJson(country_continent, file);
            }
            connection.disconnect();
        } catch (Exception e) {
            System.err.println("Ошибка при загрузке данных: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        load_countries("continent.json");
    }
}
