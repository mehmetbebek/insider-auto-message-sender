package com.insider.automessagesender.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageSchedulerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void changeMessageSendStatus_validRequestToEnable_shouldReturnSuccess() throws Exception {
        mockMvc.perform(put("/api/v1/scheduler?enabled=true")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Message scheduler enabled: true"));
    }

    @Test
    public void changeMessageSendStatus_validRequestToDisable_shouldReturnSuccess() throws Exception {
        mockMvc.perform(put("/api/v1/scheduler?enabled=false")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Message scheduler enabled: false"));
    }
}