package com.borcuhabobra.weatherbot;

import netscape.javascript.JSObject;
import org.glassfish.jersey.server.Uri;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    //b8a1b7610d9f6e7a3f3a9c44ecfe9ba6
    public static String getWeather(String message, Model model) throws IOException {

        String API_CALL_TEMPLATE = "https://api.openweathermap.org/data/2.5/weather?q=";
        String city = "";
        String API_KEY_TEMPLATE = "&units=metric&appid=b8a1b7610d9f6e7a3f3a9c44ecfe9ba6";
        String urlString = API_CALL_TEMPLATE + message + API_KEY_TEMPLATE;
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

       // int responseCode = connection.getResponseCode();

        //if (responseCode == 404) {
          //  throw new IllegalArgumentException();
        //}

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;

        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }

        in.close();

        JSONObject object = new JSONObject(response.toString());
        model.setName(object.getString("name"));

        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length();i++){
            JSONObject obj = getArray.getJSONObject(i);
            model.setIcon((String) obj.get("icon"));
            model.setMain((String) obj.get("main"));
        }

       return "City: " + model.getName() + "\n" +
               "Temperature: "+ model.getTemp() + "C"+ "\n" +
               "Humidity: " + model.getHumidity() +"%" + "\n"+
               "Main: " + model.getMain() + "\n"+
                "http://openweathermap.org/img/wn/" + model.getIcon()+".png";

    }

}
