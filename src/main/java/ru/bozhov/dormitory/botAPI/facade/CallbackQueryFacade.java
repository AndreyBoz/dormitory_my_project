package ru.bozhov.dormitory.botAPI.facade;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.botAPI.state.DepositState;
import ru.bozhov.dormitory.model.User;
import ru.bozhov.dormitory.repository.UserRepository;
import ru.bozhov.dormitory.service.UserService;

@Service
@Slf4j
public class CallbackQueryFacade {
    @Autowired
    private UserService userService;

    @Autowired
    private DepositStateContext depositStateContext;

    @Autowired
    private UserRepository userRepository;
    public SendMessage handleMessage(Update update) {
        DepositState depositState = getDepositFromCallbackQuery(update.getCallbackQuery());

        return depositStateContext.getReplyMessage(depositState, update.getCallbackQuery());
    }
    private DepositState getDepositFromCallbackQuery(CallbackQuery callbackQuery) {
        if (callbackQuery.getData().contains("approve")){
            return DepositState.ACCEPTED;
        }else if(callbackQuery.getData().contains("reject")){
            return DepositState.CANCELED;
        }
        return DepositState.WAITING;
    }
}
