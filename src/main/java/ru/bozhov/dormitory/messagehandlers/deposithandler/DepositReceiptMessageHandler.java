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
import ru.bozhov.dormitory.botAPI.state.DepositState;
import ru.bozhov.dormitory.messagehandlers.InputMessageHandler;
import ru.bozhov.dormitory.model.Deposit;
import ru.bozhov.dormitory.model.User;
import ru.bozhov.dormitory.service.DepositService;
import ru.bozhov.dormitory.service.UserService;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Component
@Slf4j
public class DepositReceiptMessageHandler implements InputMessageHandler {
    @Autowired
    DepositService depositService;
    @Autowired
    UserService userService;
    @Override
    public BotState getHandlerName() {
        return BotState.DEPOSIT_PHOTO;
    }

    @Override
    public SendMessage handle(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());

        User user = userService.getUser(message);
        sendMessage.setText("Операция не прошла.");
        if(message.hasPhoto() && user!=null){
            depositService.saveDeposit(message);

            sendMessage.setText("Операция прошла успешно, ожидайте ответа.");
        }

        return sendMessage;
    }

}
