package com.telegrambot.demo;

import com.telegrambot.demo.models.City;
import com.telegrambot.demo.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import javax.annotation.PostConstruct;

@Component
public class Bot extends TelegramLongPollingBot {

    private static final String token = "852147039:AAGVcKeN1-13PdkHS-4XWwhFmsuni_JVH0U";
    private static final String name = "t.me/eastaveracruz_bot";

    @Autowired
    private CityService cityService;

    static {
        ApiContextInitializer.init();
    }

    @PostConstruct
    private void configuring() {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public void onUpdateReceived(Update update) {
        String inMessage = update.getMessage().getText();
        City city = cityService.findCity(inMessage);
        String outMessage = city != null ? city.getDescription() : "not in the database";
        sendMsg(update.getMessage().getChatId().toString(), outMessage);
    }

    private synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
