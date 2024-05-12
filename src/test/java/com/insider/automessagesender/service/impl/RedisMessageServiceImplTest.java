package com.insider.automessagesender.service.impl;

import com.insider.automessagesender.entity.RedisMessage;
import com.insider.automessagesender.repository.RedisMessageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RedisMessageServiceImplTest {

    @Mock
    private RedisMessageRepository redisMessageRepository;

    @InjectMocks
    private RedisMessageServiceImpl redisMessageService;

    @Test
    void createMessage_ValidMessageId_ShouldSaveRedisMessage() {
        String messageId = "test-message-id";

        redisMessageService.createMessage(messageId);

        verify(redisMessageRepository, times(1)).save(any(RedisMessage.class));
    }

    @Test
    void createMessage_NullMessageId_ShouldThrowIllegalArgumentException() {
        String messageId = null;

        assertThrows(IllegalArgumentException.class, () -> redisMessageService.createMessage(messageId));
        verify(redisMessageRepository, times(0)).save(any(RedisMessage.class));
    }

    @Test
    void createMessage_EmptyMessageId_ShouldThrowIllegalArgumentException() {
        String messageId = "";

        assertThrows(IllegalArgumentException.class, () -> redisMessageService.createMessage(messageId));
        verify(redisMessageRepository, times(0)).save(any(RedisMessage.class));
    }
}