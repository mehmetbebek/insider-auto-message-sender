package com.insider.automessagesender.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "message")
public class MessageProperties {

    private boolean enabledScheduler;

    private String webhookToken;
}
