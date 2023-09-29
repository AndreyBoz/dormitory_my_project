package ru.bozhov.dormitory.messagehandlers.billinghandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.messagehandlers.InputMessageHandler;
import ru.bozhov.dormitory.service.UserService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
@Component
public class BillingPeriodStartMessageHandler implements InputMessageHandler {
    @Autowired
    private UserService userService;

    @Override
    public BotState getHandlerName() {
        return BotState.SET_BILLING_PERIOD_START;
    }

    @Override
    public SendMessage handle(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());

        Date date = tryParceDate(message.getText());
        if(date==null){
            sendMessage.setText("Укажите дату в нужном формате (день-месяц-год).");
        }else {
            userService.setBillingPeriodStart(message, java.sql.Date.valueOf(String.valueOf(date)));
            LocalDate newLocalDate = ((java.sql.Date) date).toLocalDate().plusMonths(1);
            userService.setBillingPeriodEnd(message, java.sql.Date.valueOf(newLocalDate));

            userService.setBotState(message,BotState.HELP);
            sendMessage.setText("Расчётный период установлен.");
        }

        return sendMessage;
    }
    private static Date tryParceDate(String dateString) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            Date date = dateFormat.parse(dateString);
            return date;
        } catch (ParseException e) {
            return null;
        }
    }
}