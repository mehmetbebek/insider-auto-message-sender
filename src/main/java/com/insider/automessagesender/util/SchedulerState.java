package com.insider.automessagesender.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class SchedulerState {
    private final AtomicBoolean schedulerRunning = new AtomicBoolean(false);

    private final AtomicBoolean firstRun = new AtomicBoolean(true);

    public boolean isSchedulerRunning() {
        return schedulerRunning.get();
    }

    public boolean setSchedulerRunning(boolean running) {
        return schedulerRunning.getAndSet(running);
    }

    public boolean isFirstRun() {
        return firstRun.get();
    }

    public boolean setFirstRun(boolean running) {
        return firstRun.getAndSet(running);
    }
}