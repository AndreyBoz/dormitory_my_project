package ru.bozhov.dormitory.callbackmessagehandlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.botAPI.state.DepositState;

public interface CallbackMessageHandler {
    DepositState getHandlerName();
    SendMessage handle(CallbackQuery callbackQuery);
}
