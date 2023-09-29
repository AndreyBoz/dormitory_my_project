package ru.bozhov.dormitory.messagehandlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.model.User;
import ru.bozhov.dormitory.service.UserService;
@Component
@Slf4j
public class UserInfoMessageHandler implements InputMessageHandler{
    @Autowired
    private UserService userService;

    @Override
    public BotState getHandlerName() {
        return BotState.USER_INFO;
    }

    @Override
    public SendMessage handle(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());

        User user = userService.getUser(message);
        sendMessage.setText("Error. Information are undefined");
        if(user!=null) {
            log.info("Username - {}; BotState - {}; Number room - {}; Billing Period {} - {};User type - {};", user.getUserName(), user.getBotState(), user.getNumberRoom(),user.getBillingPeriodStart(), user.getBillingPeriodEnd(),user.getType());
            sendMessage.setText(String.format("Username - %s\nBotState - %s\nNumber room - %s\nBilling Period %s - %s\nUser type - %s\n", user.getUserName(), user.getBotState(), user.getNumberRoom(),user.getBillingPeriodStart(), user.getBillingPeriodEnd(),user.getType()));
        }
        return sendMessage;
    }
}
