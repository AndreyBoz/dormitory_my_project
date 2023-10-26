package ru.bozhov.dormitory.botAPI;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.bozhov.dormitory.botAPI.facade.TelegramFacade;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.model.User;
import ru.bozhov.dormitory.service.AdminReplyService;
import ru.bozhov.dormitory.service.DepositService;
import ru.bozhov.dormitory.service.PhotoMessageService;
import ru.bozhov.dormitory.service.UserService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class DormitoryBot extends TelegramWebhookBot {
    private String botPath;
    private String botName;
    private String botToken;
    private TelegramFacade telegramFacade;
    @Autowired
    UserService userService;
    @Autowired
    PhotoMessageService photoMessageService;
    @Autowired
    AdminReplyService adminReplyService;
    @Autowired
    DepositService depositService;

    public DormitoryBot(TelegramFacade telegramFacade){
        this.telegramFacade = telegramFacade;
    }
    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasPhoto()) {
            saveReceipt(update.getMessage());

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(update.getMessage().getChatId());
            sendMessage.setText("Оплата прнята, ожидайте подтверждение администратором.");
            return sendMessage;
        }

        SendMessage message = telegramFacade.handleMessage(update);
        return message;
    }
    public void saveReceipt(Message message) {
        User user  = userService.getUser(message);
        if (user.getBotState() != BotState.DEPOSIT_PHOTO) {
            List<PhotoSize> photoSizeList = message.getPhoto();
            PhotoSize photoSize = photoSizeList.get(photoSizeList.size() - 1);
            try {
                GetFile getFile = new GetFile(photoSize.getFileId());
                org.telegram.telegrambots.meta.api.objects.File file = execute(getFile);
                File localFile = photoMessageService.savePhoto(file, photoSize,botToken);
                Long depositId = depositService.saveDeposit(message);

                executeMessage(adminReplyService.getTextNotify(user));
                executePhoto(adminReplyService.getReceiptPhoto(user, localFile,depositId));
            } catch (TelegramApiException e) {
                log.error(String.valueOf(e));
            }
        }
    }
    private void executeMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    private void executePhoto(SendPhoto sendPhoto){
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getBotPath() {
        return botPath;
    }
    @Override
    public String getBotUsername() {
        return botName;
    }
}
