package ru.bozhov.dormitory.service;

import ru.bozhov.dormitory.botAPI.state.BotState;

public interface UserService {
    boolean setBotState(Long userId, BotState botState);
}
