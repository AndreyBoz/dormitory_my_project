package ru.bozhov.dormitory.botAPI.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bozhov.dormitory.botAPI.state.DepositState;

public interface DepositService {
    Long saveDeposit(Message message);

    void setDepositState(Long depositId, DepositState depositState);
}
