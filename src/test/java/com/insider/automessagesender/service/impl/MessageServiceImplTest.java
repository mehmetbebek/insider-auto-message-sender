package com.insider.automessagesender.service.impl;

import com.insider.automessagesender.dto.MessageRequestDto;
import com.insider.automessagesender.dto.MessageResponseDto;
import com.insider.automessagesender.dto.WebhookResponseDto;
import com.insider.automessagesender.entity.Message;
import com.insider.automessagesender.repository.MessageRepository;
import com.insider.automessagesender.util.WebhookResponseStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private WebhookMessageSenderImpl webhookMessageSender;

    @Mock
    private RedisMessageServiceImpl redisMessageService;

    @InjectMocks
    private MessageServiceImpl messageService;

    private List<Message> unsentMessages;

    @BeforeEach
    void setUp() {
        unsentMessages = Arrays.asList(
                new Message(1L, "Test Message 1", "1234567890", false),
                new Message(2L, "Test Message 2", "0987654321", false)
        );
    }

    @Test
    void sendPendingMessages_NoUnsentMessages() {
        when(messageRepository.findTop2BySent(false)).thenReturn(Collections.emptyList());

        messageService.sendPendingMessages();

        verify(messageRepository, never()).save(any(Message.class));
        verify(redisMessageService, never()).createMessage(anyString());
    }

    @Test
    void sendPendingMessages_UnsentMessagesFound() {
        when(messageRepository.findTop2BySent(false)).thenReturn(unsentMessages);
        when(webhookMessageSender.sendMessage(any(Message.class)))
                .thenReturn(new WebhookResponseDto(WebhookResponseStatus.ACCEPTED, "1"))
                .thenReturn(new WebhookResponseDto(WebhookResponseStatus.REJECTED, "2"));

        messageService.sendPendingMessages();

        verify(messageRepository, times(1)).save(unsentMessages.get(0));
        verify(redisMessageService, times(1)).createMessage("1");
        verify(messageRepository, never()).save(unsentMessages.get(1));
        verify(redisMessageService, never()).createMessage("2");
    }

    @Test
    void saveMessage() {
        MessageRequestDto requestDto = new MessageRequestDto("Test Content", "1234567890");

        messageService.saveMessage(requestDto);

        verify(messageRepository, times(1)).save(any(Message.class));
    }

    @Test
    void getMessages_NoFilter() {
        List<Message> allMessages = Arrays.asList(
                new Message(1L, "Test Message 1", "1234567890", true),
                new Message(2L, "Test Message 2", "0987654321", false)
        );
        when(messageRepository.findAll()).thenReturn(allMessages);

        List<MessageResponseDto> result = messageService.getMessages(null);

        assertEquals(2, result.size());
        assertEquals("Test Message 1", result.get(0).getMessage());
        assertEquals("Test Message 2", result.get(1).getMessage());
    }

    @Test
    void getMessages_FilterBySent() {
        List<Message> sentMessages = Collections.singletonList(
                new Message(1L, "Test Message 1", "1234567890", true)
        );
        when(messageRepository.findAllBySent(true)).thenReturn(sentMessages);

        List<MessageResponseDto> result = messageService.getMessages(true);

        assertEquals(1, result.size());
        assertEquals("Test Message 1", result.get(0).getMessage());
    }
}