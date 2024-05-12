package com.insider.automessagesender.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class SchedulerState {
    private final AtomicBoolean schedulerRunning = new AtomicBoolean(false);

    public boolean isSchedulerRunning() {
        return schedulerRunning.get();
    }

    public boolean setSchedulerRunning(boolean running) {
        return schedulerRunning.getAndSet(running);
    }
}