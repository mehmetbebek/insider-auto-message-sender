package com.insider.automessagesender.service;

import com.insider.automessagesender.dto.MessageResponseDto;
import com.insider.automessagesender.dto.WebhookResponseDto;
import com.insider.automessagesender.entity.Message;
import com.insider.automessagesender.repository.MessageRepository;
import com.insider.automessagesender.util.WebhookResponseStatus;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {
    private static final Logger log = LoggerFactory.getLogger(MessageService.class);
    private final MessageRepository messageRepository;
    private final WebhookMessageSender webhookMessageSender;
    private final RedisMessageService redisMessageService;

    @Transactional
    public void sendPendingMessages() {
        log.info("Sending pending Messages");

        List<Message> unsentMessages = messageRepository.findTop2BySent(false);

        if (!CollectionUtils.isEmpty(unsentMessages)) {
            for (Message message : unsentMessages) {
                WebhookResponseDto webhookResponseDto = webhookMessageSender.sendMessage(message);

                if (WebhookResponseStatus.ACCEPTED.equals(webhookResponseDto.getMessage())) {
                    message.setSent(true);
                    messageRepository.save(message);

                    redisMessageService.createMessage(webhookResponseDto.getMessageId());
                }
            }
        } else {
            log.info("No unsent messages found");
        }
    }

    public void saveMessage(Message message) {
        messageRepository.save(message);
    }

    public List<MessageResponseDto> getSentMessages() {
        List<Message> sentMessages = messageRepository.findAllBySent(true);

        return sentMessages.stream().map(item -> new MessageResponseDto(item.getContent())).toList();
    }
}
