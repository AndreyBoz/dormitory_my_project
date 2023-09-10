package ru.bozhov.dormitory.botAPI;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bozhov.dormitory.botAPI.facade.TelegramFacade;

@Service
@Data
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
        SendMessage replyMessageToUser = telegramFacade.handleMessage(update);

        return replyMessageToUser;
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
