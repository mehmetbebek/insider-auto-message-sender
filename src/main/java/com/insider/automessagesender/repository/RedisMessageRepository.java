package com.insider.automessagesender.repository;

import com.insider.automessagesender.entity.RedisMessage;
import org.springframework.data.repository.CrudRepository;

public interface RedisMessageRepository extends CrudRepository<RedisMessage, Long> {
}
