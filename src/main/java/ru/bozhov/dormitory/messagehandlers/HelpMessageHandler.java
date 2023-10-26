package ru.bozhov.dormitory.messagehandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource(value = "classpath:bot-replays-ru.properties", encoding = "UTF-8")
public class HelpMessageHandler implements InputMessageHandler {
    @Autowired
    private UserService userService;

    @Value(value = "${help.info.ru}")
    private String HELP;
    @Override
    public BotState getHandlerName() {
        return BotState.HELP;
    }

    @Override
    public SendMessage handle(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setSelective(true);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("Зарегистрировать комнату"));
        row1.add(new KeyboardButton("Оплатить"));

        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("Установить расчетный период"));
        row2.add(new KeyboardButton("Помощь"));

        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton("Информация"));

        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);

        keyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(keyboardMarkup);

        sendMessage.setText(HELP);

        return sendMessage;
    }
}
