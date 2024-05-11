package com.insider.automessagesender.scheduler;

import com.insider.automessagesender.service.MessageService;
import com.insider.automessagesender.util.MessageProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
@Configuration
@EnableScheduling
public class ScheduledJob {

    private final MessageService messageService;

    private final MessageProperties messageProperties;

    @Scheduled(fixedDelay = 30000) //120000 2 minutes
    public void sendMessages() {
        if (messageProperties.isEnabledScheduler()) {
            messageService.sendPendingMessages();
        }
    }
}
