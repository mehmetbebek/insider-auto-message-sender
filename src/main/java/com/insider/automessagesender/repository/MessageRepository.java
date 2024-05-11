package com.insider.automessagesender.repository;

import com.insider.automessagesender.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findTop2BySent(boolean sent);

    List<Message> findAllBySent(boolean sent);
}
