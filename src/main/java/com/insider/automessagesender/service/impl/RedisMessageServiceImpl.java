package com.insider.automessagesender.service.impl;

import com.insider.automessagesender.entity.RedisMessage;
import com.insider.automessagesender.repository.RedisMessageRepository;
import com.insider.automessagesender.service.RedisMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class RedisMessageServiceImpl implements RedisMessageService {
    private final RedisMessageRepository redisMessageRepository;

    @Override
    public void createMessage(String messageId) {
        if (StringUtils.hasText(messageId)) {
            RedisMessage redisMessage = RedisMessage.builder().messageId(messageId).build();

            redisMessageRepository.save(redisMessage);
        } else {
            throw new IllegalArgumentException("Message id cannot be empty");
        }
    }
}
