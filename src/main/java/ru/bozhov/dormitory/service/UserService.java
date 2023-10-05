package ru.bozhov.dormitory.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.model.User;

import java.sql.Date;

public interface UserService {
    void setBotState(Message message, BotState botState);
    void setRoom(Message message, Integer numberRoom);
    boolean isRoomInitialized(Message message);
    BotState getBotStateByChatId(Long chatId);
    void setBillingPeriodStart(Message message, Date date);
    void setBillingPeriodEnd(Message message, Date date);
    User getUser(Message message);

    boolean isBillingPeriodInitialized(Message message);
}
