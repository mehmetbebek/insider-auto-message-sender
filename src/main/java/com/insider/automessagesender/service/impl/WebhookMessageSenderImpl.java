package com.insider.automessagesender.service.impl;

import com.insider.automessagesender.dto.WebhookRequestDto;
import com.insider.automessagesender.dto.WebhookResponseDto;
import com.insider.automessagesender.entity.Message;
import com.insider.automessagesender.service.WebhookMessageSender;
import com.insider.automessagesender.util.MessageProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebhookMessageSenderImpl implements WebhookMessageSender {
    private final RestTemplate restTemplate;
    private final MessageProperties messageProperties;

    public WebhookResponseDto sendMessage(Message message) {
        String url = messageProperties.getWebhookUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<WebhookRequestDto> request = new HttpEntity<>(new WebhookRequestDto(message), headers);

        return restTemplate.postForObject(url, request, WebhookResponseDto.class);
    }
}
