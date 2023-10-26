package ru.bozhov.dormitory.botAPI.callbackmessagehandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bozhov.dormitory.botAPI.state.DepositState;
import ru.bozhov.dormitory.service.DepositService;

import java.util.LongSummaryStatistics;

@Component
public class ApproveCallbackMessageHandler implements CallbackMessageHandler{
    @Autowired
    DepositService depositService;
    @Override
    public DepositState getHandlerName() {
        return DepositState.ACCEPTED;
    }

    @Override
    public SendMessage handle(CallbackQuery callbackQuery) {
        SendMessage sendMessage = new SendMessage();
        Long chatId = Long.valueOf(callbackQuery.getData().split(" ")[1]);
        Long depositId = Long.valueOf(String.valueOf(Long.valueOf(callbackQuery.getData().split(" ")[3])));

        sendMessage.setChatId(chatId);
        sendMessage.setText("Оплата подтверждена администратором.");

        depositService.setDepositState(depositId, DepositState.ACCEPTED);

        return sendMessage;
    }

}
