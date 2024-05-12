package com.insider.automessagesender.controller;

import com.insider.automessagesender.dto.MessageRequestDto;
import com.insider.automessagesender.dto.MessageResponseDto;
import com.insider.automessagesender.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Message", description = "Message management APIs")
@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
@Validated
public class MessageController {

    private final MessageService messageService;

    @Operation(
            summary = "Create a new Message",
            description = "Create a new message with proper content and recipientPhoneNumber. There is a phone number validation",
            tags = {"messages", "post"})
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201", content = { @Content(
                            schema = @Schema(),
                    mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "400",
                    description = "Expectation failed for empty or longer messages and/or not proper phone number",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping
    public ResponseEntity<Void> saveMessage(@Valid @RequestBody MessageRequestDto messageRequest) {
        messageService.saveMessage(messageRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(
            summary = "Get Messages by sent(optional)",
            tags = {"messages", "get", "filter"})
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = { @Content(
                            schema = @Schema(implementation = MessageResponseDto.class),
                            mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "204",
                    description = "There are no messages",
                    content = {@Content(schema = @Schema())}),
            @ApiResponse(
                    responseCode = "500",
                    content = {@Content(schema = @Schema())})})
    @GetMapping
    public ResponseEntity<List<MessageResponseDto>> getMessages(@RequestParam(required = false) Boolean sent) {
        List<MessageResponseDto> messages = messageService.getMessages(sent);

        if (CollectionUtils.isEmpty(messages)) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(messages);
    }
}
