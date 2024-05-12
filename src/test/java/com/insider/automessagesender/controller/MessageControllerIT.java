package com.insider.automessagesender.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insider.automessagesender.dto.MessageRequestDto;
import com.insider.automessagesender.dto.MessageResponseDto;
import com.insider.automessagesender.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MessageController.class)
public class MessageControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSaveMessage_validRequest_shouldReturnCreated() throws Exception {
        MessageRequestDto requestDto = new MessageRequestDto("A Valid Content","05453718713");

        String requestAsString = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/api/v1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestAsString))
                .andExpect(status().isCreated());
    }

    @Test
    public void testSaveMessage_missingMessageContent_shouldReturnBadRequest() throws Exception {
        MessageRequestDto requestDto = new MessageRequestDto("","+905453718713");

        String requestAsString = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/api/v1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestAsString))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSaveMessage_tooLongMessageContent_shouldReturnBadRequest() throws Exception {
        MessageRequestDto requestDto = new MessageRequestDto("Vestibulum interdum ligula ut iaculis suscipit. Donec eget justo ac leo laoreet laoreet vitae ac enim. Mauris eget ex luctus diam porttitor faucibus. Aenean feugiat odio in felis vulputate, in feugiat sem aliquet. Vivamus tempus est sed quam sodales faucibus ac vulputate mauris. Integer mollis mauris non purus mattis imperdiet. Proin quis fermentum massa, sit amet fringilla felis. Fusce non ullamcorper nibh. Aliquam scelerisque rhoncus egestas","+905453718713");

        String requestAsString = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/api/v1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestAsString))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSaveMessage_missingPhoneNumber_shouldReturnBadRequest() throws Exception {
        MessageRequestDto requestDto = new MessageRequestDto("","+905453718713");

        String requestAsString = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/api/v1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestAsString))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testSaveMessage_invalidPhoneNumber_shouldReturnBadRequest() throws Exception {
        MessageRequestDto requestDto = new MessageRequestDto("A valid message content","A dummy phone number");

        String requestAsString = objectMapper.writeValueAsString(requestDto);

        mockMvc.perform(post("/api/v1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestAsString))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetMessages_noQueryParam_shouldReturnAllMessages() throws Exception {
        List<MessageResponseDto> messages = Arrays.asList(
                new MessageResponseDto( "Message 1"),
                new MessageResponseDto("Message 2")
        );

        when(messageService.getMessages(null)).thenReturn(messages);

        mockMvc.perform(get("/api/v1/messages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].message", equalTo("Message 1")))
                .andExpect(jsonPath("$[1].message", equalTo("Message 2")));
    }

    @Test
    public void testGetMessages_withSentQueryParam_shouldReturnSentMessages() throws Exception {
        List<MessageResponseDto> sentMessages = List.of(new MessageResponseDto("Message 1"));

        when(messageService.getMessages(true)).thenReturn(sentMessages);

        mockMvc.perform(get("/api/v1/messages?sent=true"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].message", equalTo("Message 1")));
    }
}