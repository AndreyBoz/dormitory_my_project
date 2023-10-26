package ru.bozhov.dormitory.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@PropertySource(value = "classpath:application.properties", encoding = "UTF-8")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DormitoryBotConfig {
    @Value("${telegram.bot.name}")
    String botName;

    @Value("${telegram.bot.token}")
    String botToken;

    @Value("${telegram.bot.webhook.path}")
    String webhookPath;
}
