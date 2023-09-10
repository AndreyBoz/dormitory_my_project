package ru.bozhov.dormitory.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@PropertySource("application.properties")
public class DormitoryBotConfig {
    @Value("telegram.bot.name")
    String botName;

    @Value("telegram.bot.tocken")
    String botTocken;

    @Value("telegram.bot.webhook.path")
    String webhookPath;
}
