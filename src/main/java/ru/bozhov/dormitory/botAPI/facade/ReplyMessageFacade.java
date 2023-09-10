package ru.bozhov.dormitory.botAPI.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bozhov.dormitory.botAPI.state.BotState;

@Service
@Slf4j
public class ReplyMessageFacade {
    public SendMessage handleMessage(Update update) {
        Message message = update.getMessage();
        String inputMsg = message.getText();
        int userId = Math.toIntExact(message.getFrom().getId());
        BotState botState;
        SendMessage replyMessage = null;

        switch (inputMsg){
            case "Показать историю пополнений":

                break;
            default: botState = BotState.DEFAULT_STATE;
        }

        return replyMessage;
    }
}
