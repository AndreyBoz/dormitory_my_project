package ru.bozhov.dormitory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u.botState FROM User u WHERE u.chatId = :chatId")
    BotState findBotStateByChatId(Long chatId);
}
