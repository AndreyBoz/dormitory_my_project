package ru.bozhov.dormitory.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bozhov.dormitory.botAPI.state.BotState;

public interface UserService {
    void setBotState(Message message, BotState botState);
    void setRoom(Message message, Integer numberRoom);
    boolean isRoomInitialized(Message message);
    BotState getBotStateByChatId(Long chatId);
}
