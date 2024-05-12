/*
package com.insider.automessagesender.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.insider.automessagesender.dto.MessageRequestDto;
import com.insider.automessagesender.dto.MessageResponseDto;
import com.insider.automessagesender.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = MessageController.class)
class MessageControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void sendMessage_shouldReturnCreated() throws Exception {
        // Given
        MessageRequestDto requestDto = new MessageRequestDto();
        requestDto.setContent("Test message");
        requestDto.setRecipientPhoneNumber("+1234567890");

        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void getMessages_shouldReturnListOfMessages() throws Exception {
        // Given
        List<MessageResponseDto> messageResponseDtoList = Arrays.asList(
                new MessageResponseDto("Message 1"),
                new MessageResponseDto("Message 2")
        );

        when(messageService.getSentMessages()).thenReturn(messageResponseDtoList);

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/messages")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // Then
        String responseContent = result.getResponse().getContentAsString();
        List<MessageResponseDto> actualResponse = objectMapper.readValue(responseContent,
                objectMapper.getTypeFactory().constructCollectionType(List.class, MessageResponseDto.class));

        // Assert
        // Add assertions to compare actualResponse with expected messageResponseDtoList
    }
}
*/
