package ru.bozhov.dormitory.botAPI.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@Slf4j
public class CallbackQueryFacade {


    public SendMessage handleMessage(Update update) {
        SendMessage message = null;

        return message;
    }
}
