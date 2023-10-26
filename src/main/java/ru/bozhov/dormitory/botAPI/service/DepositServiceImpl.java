package ru.bozhov.dormitory.botAPI.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bozhov.dormitory.botAPI.DormitoryBot;
import ru.bozhov.dormitory.model.Deposit;
import ru.bozhov.dormitory.model.User;
import ru.bozhov.dormitory.repository.DepositRepository;
import ru.bozhov.dormitory.repository.UserRepository;
import ru.bozhov.dormitory.botAPI.state.DepositState;
import ru.bozhov.dormitory.service.DepositService;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;


@Service
@Slf4j
public class DepositServiceImpl implements DepositService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepositRepository depositRepository;

    @Transactional
    @Override
    public Long saveDeposit(Message message) {
        Long chatId = message.getChatId();
        User user = userRepository.findById(chatId).orElse(null);

        if (user != null) {
            Calendar calendar = Calendar.getInstance();
            Date depositDate = new Date(calendar.getTime().getTime());

            Deposit deposit = new Deposit();
            deposit.setUser(user);
            deposit.setDepositDate(depositDate);
            deposit.setDepositState(DepositState.WAITING);
            deposit.setFilePath(message.getPhoto().get(0).getFileId());
            user.getDepositData().add(deposit);

            try {
                depositRepository.save(deposit);
            } catch (Exception e) {
                log.error("Ошибка при сохранении Deposit: " + e.getMessage(), e);
            }
            return deposit.getDepositId();
        }
        return null;
    }

    @Override
    public void setDepositState(Long depositId, DepositState depositState) {
        Deposit deposit = depositRepository.findById(depositId).orElse(null);
        if(deposit!=null){
            deposit.setDepositState(depositState);
            depositRepository.save(deposit);
        }
    }
}
