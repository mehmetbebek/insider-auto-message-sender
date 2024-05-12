package com.insider.automessagesender.service;

import com.insider.automessagesender.dto.WebhookResponseDto;
import com.insider.automessagesender.entity.Message;

public interface WebhookMessageSender {
    WebhookResponseDto sendMessage(Message message);
}
