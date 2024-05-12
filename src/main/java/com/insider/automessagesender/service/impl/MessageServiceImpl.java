package com.insider.automessagesender.service.impl;

import com.insider.automessagesender.dto.MessageRequestDto;
import com.insider.automessagesender.dto.MessageResponseDto;
import com.insider.automessagesender.dto.WebhookResponseDto;
import com.insider.automessagesender.entity.Message;
import com.insider.automessagesender.repository.MessageRepository;
import com.insider.automessagesender.service.MessageService;
import com.insider.automessagesender.util.WebhookResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final WebhookMessageSenderImpl webhookMessageSender;
    private final RedisMessageServiceImpl redisMessageService;

    @Transactional
    @Override
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

    @Override
    public void saveMessage(MessageRequestDto messageRequest) {
        Message message = new Message();
        message.setContent(messageRequest.getContent());
        message.setRecipientPhoneNumber(messageRequest.getRecipientPhoneNumber());
        message.setSent(false);

        messageRepository.save(message);
    }

    @Override
    public List<MessageResponseDto> getMessages(Boolean sent) {
        List<Message> messages;

        if (sent == null) {
            messages = messageRepository.findAll();
        } else {
            messages = messageRepository.findAllBySent(sent);
        }

        return messages.stream().map(item -> new MessageResponseDto(item.getContent())).toList();
    }
}
