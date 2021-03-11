package com.borcuhabobra.weatherbot;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EchoBot extends TelegramLongPollingBot {

    private String token;
    private String username;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public EchoBot(final String token, final String botUsername, final DefaultBotOptions options){

        super(Objects.requireNonNull(options));
        this.token = Objects.requireNonNull(token);
        this.username = Objects.requireNonNull(botUsername);

    }


    @Override
    public void onUpdateReceived(Update update) {
        Model model = new Model();
        Message userMessage = update.getMessage();
        if (userMessage != null && userMessage.hasText()) {
            switch (userMessage.getText()) {
                case "/help":
                    sendMsg(userMessage, "CHO HOCHESH?");
                    break;
                case "/setting":
                    sendMsg(userMessage, "SHO NASTROIT?");
                    break;
                default:

                        try {
                            sendMsg(userMessage, Weather.getWeather(userMessage.getText(), model));
                        } catch (IOException e) {
                            sendMsg(userMessage, "No City Loh");
                        }
            }
        }
    }

    public void setButton(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardRowFirstRow = new KeyboardRow();


        keyboardRowFirstRow.add(new KeyboardButton("/help"));
        keyboardRowFirstRow.add(new KeyboardButton("/setting"));

        keyboardRowList.add(keyboardRowFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }


    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);


        try {
            setButton(sendMessage);
            execute(sendMessage);

        } catch (TelegramApiException e){
            e.printStackTrace();
        }

    }
}
