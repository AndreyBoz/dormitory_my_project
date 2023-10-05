package ru.bozhov.dormitory.botAPI;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bozhov.dormitory.botAPI.facade.TelegramFacade;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class DormitoryBot extends TelegramWebhookBot {
    private String botPath;
    private String botName;
    private String botToken;
    private TelegramFacade telegramFacade;

    public DormitoryBot(TelegramFacade telegramFacade){
        this.telegramFacade = telegramFacade;
    }
    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if(update.hasMessage() &&( update.getMessage().hasPhoto() ||update.getMessage().hasDocument())){

        }

        return telegramFacade.handleMessage(update);
    }
    public void uploadFile(String fileId){
        
    }
    @Override
    public String getBotPath() {
        return botPath;
    }
    @Override
    public String getBotUsername() {
        return botName;
    }
}
