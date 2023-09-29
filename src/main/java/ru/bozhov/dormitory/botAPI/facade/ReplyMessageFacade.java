package ru.bozhov.dormitory.botAPI.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.repository.UserRepository;
import ru.bozhov.dormitory.service.UserService;

@Service
@Slf4j
public class ReplyMessageFacade {
    @Autowired
    private UserService userService;

    @Autowired
    private BotStateContext botStateContext;

    @Autowired
    private UserRepository userRepository;
    public SendMessage handleMessage(Update update) {
        Message message = update.getMessage();
        String inputMsg = message.getText();
        BotState botState = getBotStateFromInputMessage(message);

        userService.setBotState(message, botState);

        return botStateContext.getReplyMessage(botState, message);
    }

    private BotState getBotStateFromInputMessage(Message message) {
        switch (message.getText()) {
            case "Зарегистрировать комнату":
                return BotState.REGISTRATION;
            /*case "Показать историю пополнений":
                return BotState.VIEW_HISTORY_DEPOSIT;*/
            case "Оплатить":
                return BotState.DEPOSIT;
            case "Установить расчётный период":
                return BotState.SET_BILLING_PERIOD;
            /*case "Посмотреть состояние оплаты":
                return BotState.SHOW_DEPOSIT_STATE;*/
            case "Помощь":
                return BotState.HELP;
            case "Информация":
                return BotState.USER_INFO;
            default:
                return userRepository.findBotStateByChatId(message.getChatId());
        }
    }
}
