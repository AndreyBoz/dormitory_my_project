package ru.bozhov.dormitory.botAPI.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@Slf4j
public class TelegramFacade {
    private CallbackQueryFacade callbackQueryFacade;
    private ReplyMessageFacade replyMessageFacade;

    public TelegramFacade(CallbackQueryFacade callbackQueryFacade){
        this.callbackQueryFacade = callbackQueryFacade;
    }

    public SendMessage handleMessage(Update update) {
        SendMessage replyMessage = null;


        if(update.hasCallbackQuery()){
            log.info("New callbackquery; Chat ID  - {}; Username - {}, Data - {}", update.getCallbackQuery().getMessage().getChatId(), update.getCallbackQuery().getMessage().getChat().getUserName(), update.getCallbackQuery().getData());
            return callbackQueryFacade.handleMessage(update);
        }

        Message message = update.getMessage();
        if(message.hasText() || message != null){
            log.info("New message; Chat ID  - {}; Username - {}; Text - {}", message.getChatId(), message.getChat().getUserName(),message.getText());
            return replyMessageFacade.handleMessage(update);
        }

        return replyMessage;
    }
}
