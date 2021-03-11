package com.borcuhabobra.weatherbot;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    //b8a1b7610d9f6e7a3f3a9c44ecfe9ba6
    public static String getWeather(String message, Model model) throws IOException {
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Omsk&appid=b8a1b7610d9f6e7a3f3a9c44ecfe9ba6");

        Scanner scanner = new Scanner((InputStream) url.getContent());
        String result = "";
        while (scanner.hasNext()){
            result += scanner.nextLine();
        }
       return result;
    }

}
