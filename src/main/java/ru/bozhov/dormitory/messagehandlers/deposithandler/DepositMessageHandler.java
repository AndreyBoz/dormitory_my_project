package ru.bozhov.dormitory.messagehandlers.deposithandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bozhov.dormitory.botAPI.DormitoryBot;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.messagehandlers.InputMessageHandler;
import ru.bozhov.dormitory.service.UserService;

import java.io.File;
import java.util.List;

@Component
@Slf4j
public class DepositMessageHandler implements InputMessageHandler {
    @Autowired
    private UserService userService;

    @Override
    public BotState getHandlerName() {
        return BotState.DEPOSIT;
    }

    @Override
    public SendMessage handle(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());

        if(userService.isRoomInitialized(message) && userService.isBillingPeriodInitialized(message)){
            sendMessage.setText("Отправьте скриншот оплаты.");
            userService.setBotState(message,BotState.DEPOSIT_PHOTO);
        }else {
            sendMessage.setText("Номер вашей комнаты или тарифный период не установлен.");
            userService.setBotState(message,BotState.HELP);
        }

        return sendMessage;
    }
}