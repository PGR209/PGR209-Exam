package com.PGR209.Exam.AddressTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    private String dateTime;

    @BeforeEach
    public void addressSetup() {
        String body;

        dateTime = LocalDateTime.now().toString();
        body = String.format("{\"street\":\"%s\"}", dateTime);

        try {
            mockMvc.perform(post("/api/address")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body));
        } catch (Exception error) {
            System.out.println("Exception during addressSetup for integration tests: " + error);
        }
    }

    @Test
    public void shouldFetchAllAddresses() {
        String expectedBody = String.format("[{\"id\":1,\"street\":\"%s\",\"customers\":[]}]", dateTime);

        try {
            mockMvc.perform(get("/api/address")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(expectedBody));
        } catch (Exception error) {
            System.out.println("Exception during shouldFetchAllAddresses test: " + error);
        }
    }

    @Test
    public void shouldDeleteOneAddress() {
        try {
            mockMvc.perform(delete("/api/address/1")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

            mockMvc.perform(get("/api/address")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json("[]"));
        } catch (Exception error) {
            System.out.println("Exception during shouldDeleteOneAddress test: " + error);
        }
    }

    @Test
    public void shouldUpdateAddress() {
        String newBody = String.format("{\"id\":1,\"street\":\"Updated: %s\",\"customers\":[]}", dateTime);

        try {
            mockMvc.perform(put("/api/address")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(newBody))
                    .andExpect(status().isOk())
                    .andExpect(content().json(newBody));
        } catch (Exception error) {
            System.out.println("Exception during shouldUpdateAddress test: " + error);
        }
    }
}
