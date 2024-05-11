package com.insider.automessagesender.dto;

import com.insider.automessagesender.util.WebhookResponseStatus;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class WebhookResponseDto {

    private WebhookResponseStatus message;

    private String messageId;
}
