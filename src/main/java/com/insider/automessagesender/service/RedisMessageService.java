package com.insider.automessagesender.service;

import com.insider.automessagesender.entity.RedisMessage;
import com.insider.automessagesender.repository.RedisMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisMessageService {
    private final RedisMessageRepository redisMessageRepository;

    public void createMessage(String messageId) {
        RedisMessage redisMessage = RedisMessage.builder().messageId(messageId).build();

        redisMessageRepository.save(redisMessage);
    }
}
