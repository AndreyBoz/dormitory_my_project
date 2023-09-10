package ru.bozhov.dormitory.botAPI.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.service.UserService;

@Service
@Slf4j
public class ReplyMessageFacade {
    UserService userService;
    BotStateContext botStateContext;


    public SendMessage handleMessage(Update update) {
        Message message = update.getMessage();
        String inputMsg = message.getText();
        int userId = Math.toIntExact(message.getFrom().getId());
        BotState botState;
        SendMessage replyMessage = null;

        switch (inputMsg){
            case "Показать историю пополнений":
                botState = BotState.VIEW_HISTORY_DEPOSIT;
                break;
            case "Помощь":
                botState = BotState.HELP;
                break;
            case "Посмотреть даты расчётного периода":
                botState = BotState.SHOW_TRANSFER_DATE;
                break;
            case "Пополнить":
                botState = BotState.DEPOSIT;
                break;
            case "Посмотреть сосотояние оплаты":
                botState = BotState.SHOW_DEPOSIT_STATE;
                break;
            default: botState = BotState.DEFAULT_STATE;
        }

        userService.setBotState(Long.valueOf(userId), botState);

        replyMessage = botStateContext.getReplyMessage(botState, message);

        return replyMessage;
    }
}
