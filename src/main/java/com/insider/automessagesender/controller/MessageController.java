package com.insider.automessagesender.controller;

import com.insider.automessagesender.dto.MessageRequestDto;
import com.insider.automessagesender.dto.MessageResponseDto;
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

    @PostMapping
    public ResponseEntity<Void> saveMessage(@Valid @RequestBody MessageRequestDto messageRequest) {
        messageService.saveMessage(messageRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<MessageResponseDto>> getMessages(@RequestParam(required = false) Boolean sent) {
        return ResponseEntity.ok(messageService.getMessages(sent));
    }
}
