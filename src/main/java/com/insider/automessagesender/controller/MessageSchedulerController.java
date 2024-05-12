package com.insider.automessagesender.controller;

import com.insider.automessagesender.scheduler.MessageScheduledTask;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/scheduler")
@RequiredArgsConstructor
@Validated
public class MessageSchedulerController {

    private final MessageScheduledTask messageScheduledTask;

    @PutMapping
    public ResponseEntity<String> changeMessageSendStatus(@RequestParam boolean status) {
        messageScheduledTask.setEnabled(status);
        return ResponseEntity.ok("Message scheduler status is changed to " + status);
    }
}
