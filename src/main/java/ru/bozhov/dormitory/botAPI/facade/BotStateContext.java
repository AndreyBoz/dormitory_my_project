package ru.bozhov.dormitory.botAPI.facade;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bozhov.dormitory.botAPI.state.BotState;

import java.time.LocalDate;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.messagehandlers.InputMessageHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BotStateContext {
    private final Map<BotState, InputMessageHandler> messageHandlers = new HashMap<>();

    public BotStateContext(List<InputMessageHandler> inputMessageHandlers) {
        for (InputMessageHandler handler : inputMessageHandlers) {
            messageHandlers.put(handler.getHandlerName(), handler);
        }
    }

    public SendMessage getReplyMessage(BotState botState, Message message) {
        InputMessageHandler currentMessageHandler = messageHandlers.get(botState);

        if (currentMessageHandler == null) {
            throw new IllegalStateException("No message handler found for state: " + botState);
        }

        return currentMessageHandler.handle(message);
    }
}

