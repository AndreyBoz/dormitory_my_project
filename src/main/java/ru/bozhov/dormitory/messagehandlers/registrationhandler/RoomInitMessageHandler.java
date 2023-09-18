package ru.bozhov.dormitory.messagehandlers.registrationhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.messagehandlers.InputMessageHandler;
import ru.bozhov.dormitory.model.User;
import ru.bozhov.dormitory.service.UserService;

@Component
@Slf4j
public class RoomInitMessageHandler implements InputMessageHandler {
    @Autowired
    private UserService userService;

    @Override
    public BotState getHandlerName() {
        return BotState.ROOM_INIT;
    }

    @Override
    public SendMessage handle(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());

        if(isValid(message.getText())){
            userService.setRoom(message,Integer.valueOf(message.getText()));
            sendMessage.setText("Комната успешно записана.");
            log.info("User - {}; Set his room - {}", message.getChatId(), message.getText());
        }else {
            sendMessage.setText("Вы не верно ввели номер комнаты.");
            log.info("User - {}; Enter not valid number room- {}", message.getChatId(), message.getText());
        }

        return sendMessage;
    }
    public static boolean isValid(String str) {
        try {
            Integer room = Integer.parseInt(str);

            if(room <= 0 || room >= 170)
                return false;

            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}