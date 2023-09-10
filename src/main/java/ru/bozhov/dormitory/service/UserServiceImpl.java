package ru.bozhov.dormitory.service;

import org.springframework.stereotype.Service;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.model.User;
import ru.bozhov.dormitory.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    UserRepository userRepository;
    @Override
    public boolean setBotState(Long userId, BotState botState) {
        User user = userRepository.findById(userId).orElse(null);

        if(user==null)
            return false;

        user.setBotState(botState);

        return true;
    }
}