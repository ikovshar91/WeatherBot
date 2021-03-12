package com.borcuhabobra.weatherbot;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather {

    //b8a1b7610d9f6e7a3f3a9c44ecfe9ba6
    public static String getWeather(String message, Model model) throws IOException {

        String API_CALL_TEMPLATE = "https://api.openweathermap.org/data/2.5/weather?q=";
        String city = "";
        String API_KEY_TEMPLATE = "&appid=b8a1b7610d9f6e7a3f3a9c44ecfe9ba6";
        String urlString = API_CALL_TEMPLATE + message + API_KEY_TEMPLATE;
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;

        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

       return response.toString();
    }

}
