package ru.bozhov.dormitory.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.bozhov.dormitory.botAPI.DormitoryBot;
import ru.bozhov.dormitory.botAPI.facade.TelegramFacade;

@Configuration
public class AppConfig {
    private final DormitoryBotConfig dormitoryBotConfig;

    public AppConfig(DormitoryBotConfig dormitoryBotConfig){
        this.dormitoryBotConfig = dormitoryBotConfig;
    }

    @Bean
    DormitoryBot dormitoryBot(TelegramFacade telegramFacade){

        DormitoryBot dormitoryBot = new DormitoryBot(telegramFacade);
        dormitoryBot.setBotPath(dormitoryBotConfig.getWebhookPath());
        dormitoryBot.setBotName(dormitoryBotConfig.getBotName());
        dormitoryBot.setBotToken(dormitoryBotConfig.getBotTocken());

        return dormitoryBot;
    }
}
