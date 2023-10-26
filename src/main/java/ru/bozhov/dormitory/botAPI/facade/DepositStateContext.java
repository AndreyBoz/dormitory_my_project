package ru.bozhov.dormitory.botAPI.facade;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.botAPI.state.DepositState;
import ru.bozhov.dormitory.callbackmessagehandlers.CallbackMessageHandler;
import ru.bozhov.dormitory.messagehandlers.InputMessageHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DepositStateContext {
    private final Map<DepositState, CallbackMessageHandler> messageHandlers = new HashMap<>();

    public DepositStateContext(List<CallbackMessageHandler> callbackMessageHandlers) {
        for (CallbackMessageHandler handler : callbackMessageHandlers) {
            messageHandlers.put(handler.getHandlerName(), handler);
        }
    }

    public SendMessage getReplyMessage(DepositState depositState, CallbackQuery callbackQuery) {
        CallbackMessageHandler currentMessageHandler = messageHandlers.get(depositState);

        if (currentMessageHandler == null) {
            throw new IllegalStateException("No message handler found for state: " + depositState);
        }

        return currentMessageHandler.handle(callbackQuery);
    }
}