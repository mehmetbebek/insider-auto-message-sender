package com.insider.automessagesender.scheduler;

import com.insider.automessagesender.service.MessageService;
import com.insider.automessagesender.util.MessageProperties;
import com.insider.automessagesender.util.SchedulerState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@EnableScheduling
@Slf4j
public class MessageScheduledTask {

    private final MessageService messageService;

    private final MessageProperties messageProperties;

    private final SchedulerState schedulerState;

    @Scheduled(fixedDelay = 2 * 60 * 1000) //120000 2 minutes
    public void sendMessages() {
        if (!schedulerState.isSchedulerRunning()) {
            schedulerState.setSchedulerRunning(true);
            try {
                if (messageProperties.isEnabledScheduler()) {
                    messageService.sendPendingMessages();
                }
            } finally {
                schedulerState.setSchedulerRunning(false);
            }
        } else {
            log.info("Scheduler is already running. Skipping execution.");
        }
    }

    public void setEnabled(boolean status) {
        messageProperties.setEnabledScheduler(status);
    }
}
