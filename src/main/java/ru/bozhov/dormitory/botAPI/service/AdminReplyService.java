package ru.bozhov.dormitory.botAPI.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import ru.bozhov.dormitory.model.User;

import java.io.File;

public interface AdminReplyService {
    SendMessage getTextNotify(User user);
    SendPhoto getReceiptPhoto(User user,File file,Long depositId);
}
