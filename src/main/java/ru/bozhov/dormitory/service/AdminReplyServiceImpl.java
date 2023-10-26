package ru.bozhov.dormitory.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.bozhov.dormitory.model.User;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AdminReplyServiceImpl implements AdminReplyService{
    @Value("${admin.chatId}")
    private String adminChatId;
    @Override
    public SendMessage getTextNotify(User user) {
        SendMessage adminMessage = new SendMessage();
        adminMessage.setChatId(adminChatId);
        adminMessage.setText(String.format("Оплата;\nКомната  - %d",user.getNumberRoom()));

        return adminMessage;
    }

    @Override
    public SendPhoto getReceiptPhoto(User user, File file, Long depositId) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setChatId(adminChatId);
        sendPhoto.setPhoto(new InputFile(file));
        // Создаем инлайн-клавиатуру
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        InlineKeyboardButton approveButton = new InlineKeyboardButton("Одобрить");
        approveButton.setCallbackData("approve: " + user.getChatId() + " id: "+depositId);
        List<InlineKeyboardButton> approveRow = new ArrayList<>();
        approveRow.add(approveButton);

        InlineKeyboardButton rejectButton = new InlineKeyboardButton("Отклонить");
        rejectButton.setCallbackData("reject: " + user.getChatId() + " id: " + depositId);
        List<InlineKeyboardButton> rejectRow = new ArrayList<>();
        rejectRow.add(rejectButton);

        keyboard.add(approveRow);
        keyboard.add(rejectRow);

        keyboardMarkup.setKeyboard(keyboard);
        sendPhoto.setReplyMarkup(keyboardMarkup);

        return sendPhoto;
    }
}
