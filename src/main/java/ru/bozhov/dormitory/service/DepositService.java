package ru.bozhov.dormitory.service;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public interface DepositService {
    void saveDeposit(Message message);
}
