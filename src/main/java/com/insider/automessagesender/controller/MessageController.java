package com.insider.automessagesender.controller;

import com.insider.automessagesender.util.MessageProperties;
import com.insider.automessagesender.dto.MessageRequestDto;
import com.insider.automessagesender.dto.MessageResponseDto;
import com.insider.automessagesender.entity.Message;
import com.insider.automessagesender.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
@Validated
public class MessageController {

    private final MessageService messageService;
    private final MessageProperties messageProperties;

    @PostMapping
    public ResponseEntity<Void> sendMessage(@Valid @RequestBody MessageRequestDto messageRequest) {
        Message message = new Message();
        message.setContent(messageRequest.getContent());
        message.setRecipientPhoneNumber(messageRequest.getRecipientPhoneNumber());
        message.setSent(false);

        messageService.saveMessage(message);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<MessageResponseDto>> getMessages() {
        List<MessageResponseDto> sentMessages = messageService.getSentMessages();
        return ResponseEntity.ok(sentMessages);
    }

    @PutMapping("/scheduler")
    public ResponseEntity<String> changeMessageSendStatus(@RequestParam boolean status) {
        messageProperties.setEnabledScheduler(status);
        return ResponseEntity.ok("Message send status changed to " + status);
    }
}
