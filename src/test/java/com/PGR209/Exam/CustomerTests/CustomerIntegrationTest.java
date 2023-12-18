package com.PGR209.Exam.CustomerTests;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    private static String dateTime;

    @BeforeAll
    public static void partSetup() {
        dateTime = LocalDateTime.now().toString();
    }

    @Test
    @Order(1)
    public void shouldFetchFirstPageEmptyCustomer() {
        try {
            mockMvc.perform(get("/api/customer/page/0")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(204));
        } catch (Exception error) {
            System.out.println("Exception during shouldFetchFirstPageEmptyCustomer test: " + error);
        }
    }

    @Test
    @Order(2)
    public void shouldCreateSingleCustomer() {
        String requestBody = String.format("{\"customerName\":\"FirstCustomer %s\",\"customerEmail\":\"@test\"}", dateTime);

        try {
            mockMvc.perform(post("/api/customer")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isCreated());
        } catch (Exception error) {
            System.out.println("Exception during shouldCreateSingleCustomer test: " + error);
        }
    }

    @Test
    @Order(3)
    public void shouldFetchFirstCustomer() {
        String expectedBody = String.format("{\"customerId\":1,\"customerName\":\"FirstCustomer %s\",\"customerEmail\":\"@test\",\"customerAddresses\":[]}", dateTime);

        try {
            mockMvc.perform(get("/api/customer/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(expectedBody));
        } catch (Exception error) {
            System.out.println("Exception during shouldFetchFirstCustomer test: " + error);
        }
    }

    @Test
    @Order(4)
    public void shouldUpdateCustomer() {
        String newBody = "{\"customerName\":\"NewValue\",\"customerEmail\":\"@new\"}";

        try {
            mockMvc.perform(put("/api/customer/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(newBody))
                    .andExpect(status().isOk())
                    .andExpect(content().json(newBody));
        } catch (Exception error) {
            System.out.println("Exception during shouldUpdateCustomer test: " + error);
        }
    }

    @Test
    @Order(5)
    public void shouldDeleteOneCustomer() {
        try {
            mockMvc.perform(delete("/api/customer/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

            mockMvc.perform(get("/api/customer/page/0")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent())
                    .andExpect(content().json("[]"));
        } catch (Exception error) {
            System.out.println("Exception during shouldDeleteOneCustomer test: " + error);
        }
    }
}
