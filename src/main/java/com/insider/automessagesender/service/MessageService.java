package com.insider.automessagesender.service;

import com.insider.automessagesender.dto.MessageRequestDto;
import com.insider.automessagesender.dto.MessageResponseDto;

import java.util.List;

public interface MessageService {
    void sendPendingMessages();

    void saveMessage(MessageRequestDto messageRequest);

    List<MessageResponseDto> getMessages(Boolean sent);
}
