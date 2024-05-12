package com.insider.automessagesender.dto;

import com.insider.automessagesender.util.WebhookResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WebhookResponseDto {

    private WebhookResponseStatus message;

    private String messageId;
}
