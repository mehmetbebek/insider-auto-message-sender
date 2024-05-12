package com.insider.automessagesender.scheduler;


import com.insider.automessagesender.service.MessageService;
import com.insider.automessagesender.util.MessageProperties;
import com.insider.automessagesender.util.SchedulerState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageScheduledTaskTest {

    @Mock
    private MessageService messageService;

    @Mock
    private MessageProperties messageProperties;

    @Mock
    private SchedulerState schedulerState;

    @InjectMocks
    private MessageScheduledTask messageScheduledTask;

    @Test
    void sendMessages_SchedulerEnabled_ShouldCallMessageService() {
        when(messageProperties.isEnabledScheduler()).thenReturn(true);
        when(schedulerState.isSchedulerRunning()).thenReturn(false);

        messageScheduledTask.sendMessages();

        verify(messageService, times(1)).sendPendingMessages(false);
        verify(schedulerState, times(1)).setSchedulerRunning(true);
        verify(schedulerState, times(1)).setSchedulerRunning(false);
    }

    @Test
    void sendMessages_SchedulerDisabled_ShouldNotCallMessageService() {
        when(messageProperties.isEnabledScheduler()).thenReturn(true);
        when(schedulerState.isSchedulerRunning()).thenReturn(false);

        when(messageProperties.isEnabledScheduler()).thenReturn(false);

        messageScheduledTask.sendMessages();

        verify(messageService, never()).sendPendingMessages(false);
        verify(schedulerState, times(1)).setSchedulerRunning(true);
        verify(schedulerState, times(1)).setSchedulerRunning(false);
    }

    @Test
    void sendMessages_SchedulerAlreadyRunning_ShouldNotCallMessageService() {
        when(schedulerState.isSchedulerRunning()).thenReturn(true);

        messageScheduledTask.sendMessages();

        verify(messageService, never()).sendPendingMessages(false);
        verify(schedulerState, never()).setSchedulerRunning(true);
        verify(schedulerState, never()).setSchedulerRunning(false);
    }

    @Test
    void setEnabled_ShouldUpdateMessageProperties() {
        boolean status = true;

        messageScheduledTask.setEnabled(status);

        verify(messageProperties, times(1)).setEnabledScheduler(status);
    }
}