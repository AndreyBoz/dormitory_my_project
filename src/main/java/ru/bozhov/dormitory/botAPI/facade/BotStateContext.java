package ru.bozhov.dormitory.botAPI.facade;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bozhov.dormitory.botAPI.state.BotState;

@Service
public class BotStateContext {

    public SendMessage getReplyMessage(BotState botState, Message message) {
        SendMessage sendMessage = null;

        return sendMessage;
    }
}
