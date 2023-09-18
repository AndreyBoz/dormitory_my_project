package ru.bozhov.dormitory.messagehandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.bozhov.dormitory.botAPI.state.BotState;
import ru.bozhov.dormitory.service.UserService;
@Component
public class HelpMessageHandler implements InputMessageHandler {
    @Autowired
    private UserService userService;

    @Override
    public BotState getHandlerName() {
        return BotState.HELP;
    }

    @Override
    public SendMessage handle(Message message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());

        sendMessage.setText("Добро пожаловать в помощь бота общежития!\n" +
                "\n" +
                "Чтобы использовать бота, вы можете отправить одну из следующих команд:\n" +
                "\n" +
                "1. \"Зарегистрировать комнату\" - для начала процедуры регистрации вашей комнаты.\n" +
                "2. \"Показать историю пополнений\" - чтобы просмотреть историю пополнений вашего счета.\n" +
                "3. \"Посмотреть даты расчётного периода\" - для просмотра информации о датах расчетного периода.\n" +
                "4. \"Оплатить\" - чтобы пополнить ваш счет.\n" +
                "5. \"Посмотреть состояние оплаты\" - для проверки текущего состояния вашей оплаты.\n" +
                "6. \"Помощь\" - чтобы получить это сообщение снова и ознакомиться с командами.\n" +
                "\n" +
                "Если у вас возникнут дополнительные вопросы или потребуется дополнительная помощь, не стесняйтесь обращаться к нам. Мы готовы помочь!\n" +
                "\n" +
                "Удачного использования!\n");

        return sendMessage;
    }
}
