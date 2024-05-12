package com.insider.automessagesender.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insider.automessagesender.entity.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class WebhookRequestDto {
    private String to;

    private String content;

    @JsonIgnore
    public WebhookRequestDto(Message message) {
        this.to = message.getRecipientPhoneNumber();
        this.content = message.getContent();
    }
}
