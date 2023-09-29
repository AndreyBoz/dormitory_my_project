package ru.bozhov.dormitory.messagehandlers.billinghandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.messagehandlers.InputMessageHandler;
import ru.bozhov.dormitory.service.UserService;
@Component
@Slf4j
public class BillingPeriodMessageHandler implements InputMessageHandler {
    @Autowired
    private UserService userService;

    @Override
    public BotState getHandlerName() {
        return BotState.SET_BILLING_PERIOD;
    }

    @Override
    public SendMessage handle(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());

        if(!userService.isRoomInitialized(message)){
            sendMessage.setText("Сначала укажите номер комнаты.");
            userService.setBotState(message,BotState.HELP);
        }else {
            sendMessage.setText("Укажите дату расчётного периода в формате \"день-месяц-год\"");
            userService.setBotState(message,BotState.SET_BILLING_PERIOD_START);
        }

        return sendMessage;
    }
}