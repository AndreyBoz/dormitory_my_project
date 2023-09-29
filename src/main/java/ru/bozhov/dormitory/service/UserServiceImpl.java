package ru.bozhov.dormitory.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.model.ClientType;
import ru.bozhov.dormitory.model.User;
import ru.bozhov.dormitory.repository.UserRepository;

import java.sql.Date;
import java.time.LocalDate;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Override
    public void setBotState(Message message, BotState botState) {
        Long chatId = (long) message.getChatId();
        User user = userRepository.findById(chatId).orElse(null);

        if(user==null) {
            user = new User();
            user.setChatId(chatId);
            user.setUserName(message.getFrom().getUserName());
            user.setType(ClientType.USER);
            user.setBotState(BotState.HELP);
            botState = BotState.HELP;
            log.info("New user; Name - {}; ChatID - {}; BotState - {};", user.getUserName(), user.getChatId(),user.getBotState());

        }
        user.setBotState(botState);

        userRepository.save(user);
    }

    @Override
    public void setRoom(Message message, Integer numberRoom) {
        User user = userRepository.findById(message.getChatId()).orElse(null);

        if(user!=null){
            user.setNumberRoom(numberRoom);
            user.setBotState(BotState.HELP);
            userRepository.save(user);
        }

    }

    @Override
    public boolean isRoomInitialized(Message message) {
        User user = userRepository.findById(message.getChatId()).orElse(null);

        if(user!=null && user.getNumberRoom()!=null) {
            return true;
        }

        return false;
    }

    @Override
    public BotState getBotStateByChatId(Long chatId) {
        BotState botState = userRepository.findBotStateByChatId(chatId);

        if(botState == null){
            botState = BotState.HELP;
        }

        return botState;
    }

    @Override
    public void setBillingPeriodStart(Message message, Date date) {
        User user = userRepository.findById(message.getChatId()).orElse(null);

        if(user!=null){
            user.setBillingPeriodStart(date);
        }
    }
    @Override
    public void setBillingPeriodEnd(Message message, Date date) {
        User user = userRepository.findById(message.getChatId()).orElse(null);

        if(user!=null){
            user.setBillingPeriodEnd(date);
        }
    }
}