package com.insider.automessagesender.service.impl;

import com.insider.automessagesender.dto.WebhookResponseDto;
import com.insider.automessagesender.entity.Message;
import com.insider.automessagesender.util.MessageProperties;
import com.insider.automessagesender.util.WebhookResponseStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WebhookMessageSenderImplTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private MessageProperties messageProperties;

    @InjectMocks
    private WebhookMessageSenderImpl webhookMessageSender;

    private Message message;
    private String webhookUrl;

    @BeforeEach
    void setUp() {
        message = new Message(1L, "Test Message", "1234567890", false);
        webhookUrl = "https://example.com/webhook";
    }

    @Test
    void sendMessage_ShouldSendWebhookRequest() {
        WebhookResponseDto expectedResponse = new WebhookResponseDto(WebhookResponseStatus.ACCEPTED, "message-id");
        when(messageProperties.getWebhookUrl()).thenReturn(webhookUrl);
        when(restTemplate.postForObject(eq(webhookUrl), any(HttpEntity.class), eq(WebhookResponseDto.class)))
                .thenReturn(expectedResponse);

        WebhookResponseDto actualResponse = webhookMessageSender.sendMessage(message);

        assertEquals(expectedResponse, actualResponse);
    }
}