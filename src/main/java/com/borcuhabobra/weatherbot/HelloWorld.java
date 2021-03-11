package com.borcuhabobra.weatherbot;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class HelloWorld {

    public static final String BOT_KEY = "1677448090:AAF-HPabg4ovgcyg217QzDa2RECYNZ6rJZQ";
    public static final String BOT_USERNAME = "WeatherTest_Kovshar_bot";


    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            DefaultBotOptions options = new DefaultBotOptions();
            telegramBotsApi.registerBot(new EchoBot(BOT_KEY,BOT_USERNAME,options));
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }
}
