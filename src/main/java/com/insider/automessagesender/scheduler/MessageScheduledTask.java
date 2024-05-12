package com.insider.automessagesender.scheduler;

import com.insider.automessagesender.service.MessageService;
import com.insider.automessagesender.util.MessageProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@EnableScheduling
public class MessageScheduledTask {

    private final MessageService messageService;

    private final MessageProperties messageProperties;

    @Scheduled(fixedDelay = 10000) //120000 2 minutes
    public void sendMessages() {
        if (messageProperties.isEnabledScheduler()) {
            messageService.sendPendingMessages();
        }
    }

    public void setEnabled(boolean status) {
        messageProperties.setEnabledScheduler(status);
    }
}
