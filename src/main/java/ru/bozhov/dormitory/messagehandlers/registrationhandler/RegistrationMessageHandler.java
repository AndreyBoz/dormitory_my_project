package ru.bozhov.dormitory.messagehandlers.registrationhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.messagehandlers.InputMessageHandler;
import ru.bozhov.dormitory.service.UserService;
import ru.bozhov.dormitory.service.UserServiceImpl;

@Component
public class RegistrationMessageHandler implements InputMessageHandler {
    @Autowired
    private UserService userService;

    @Override
    public BotState getHandlerName() {
        return BotState.REGISTRATION;
    }

    @Override
    public SendMessage handle(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());

        if(!userService.isRoomInitialized(message)) {
            sendMessage.setText("Введите номер комнаты");
            userService.setBotState(message,BotState.ROOM_INIT);
        }else {
            sendMessage.setText("Ваша комната зарегистрирована.");
            userService.setBotState(message, BotState.HELP);
        }

        return sendMessage;
    }
}
