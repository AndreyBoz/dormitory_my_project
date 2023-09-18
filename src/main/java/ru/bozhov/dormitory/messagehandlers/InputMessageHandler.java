package ru.bozhov.dormitory.messagehandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.service.UserServiceImpl;

public interface InputMessageHandler {
    BotState getHandlerName();
    SendMessage handle(Message message);
}
