package com.insider.automessagesender.controller;

import com.insider.automessagesender.scheduler.MessageScheduledTask;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Scheduler", description = "Message scheduler management APIs")
@RestController
@RequestMapping("/api/v1/scheduler")
@RequiredArgsConstructor
@Validated
public class MessageSchedulerController {

    private final MessageScheduledTask messageScheduledTask;

    @Operation(
            summary = "Enable or disable message send scheduler",
            tags = {"scheduler", "put"})
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", content = {@Content(
                    schema = @Schema(implementation = String.class),
                    mediaType = "application/json")}),
            @ApiResponse(
                    responseCode = "500",
                    content = {@Content(schema = @Schema())})})
    @PutMapping
    public ResponseEntity<String> changeMessageSendStatus(@RequestParam boolean enabled) {
        messageScheduledTask.setEnabled(enabled);
        return ResponseEntity.ok("Message scheduler enabled: " + enabled);
    }
}
