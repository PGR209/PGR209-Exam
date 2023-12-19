package com.PGR209.Exam.SubassemblyTests;

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
public class SubassemblyIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    private static String dateTime;

    @BeforeAll
    public static void subassemblySetup() {
        dateTime = LocalDateTime.now().toString();
    }

    @Test
    @Order(1)
    public void shouldFetchFirstPageEmptySubassembly() {
        try {
            mockMvc.perform(get("/api/subassembly/page/0")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(204));
        } catch (Exception error) {
            System.out.println("Exception during shouldFetchFirstPageEmptySubassembly test: " + error);
        }
    }

    @Test
    @Order(2)
    public void shouldCreateSingleSubassembly() {
        String requestBody = String.format("{\"subassemblyName\":\"FirstSubassembly %s\"}", dateTime);

        try {
            mockMvc.perform(post("/api/subassembly")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isCreated());
        } catch (Exception error) {
            System.out.println("Exception during shouldCreateSingleSubassembly test: " + error);
        }
    }

    @Test
    @Order(3)
    public void shouldFetchFirstSubassembly() {
        String expectedBody = String.format("{\"subassemblyId\":1,\"subassemblyName\":\"FirstSubassembly %s\",\"subassemblyParts\":[]}", dateTime);

        try {
            mockMvc.perform(get("/api/subassembly/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(expectedBody));
        } catch (Exception error) {
            System.out.println("Exception during shouldFetchFirstSubassembly test: " + error);
        }
    }

    @Test
    @Order(4)
    public void shouldUpdateSubassembly() {
        String newBody = "{\"subassemblyName\":\"NewValue\"}";

        try {
            mockMvc.perform(put("/api/subassembly/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(newBody))
                    .andExpect(status().isOk())
                    .andExpect(content().json(newBody));
        } catch (Exception error) {
            System.out.println("Exception during shouldUpdateSubassembly test: " + error);
        }
    }

    @Test
    @Order(5)
    public void shouldDeleteOneSubassemblyAndCustomerAndAddresses() {
        try {
            mockMvc.perform(delete("/api/subassembly/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

            mockMvc.perform(delete("/api/customer/1")
                            .contentType(MediaType.APPLICATION_JSON));

            mockMvc.perform(delete("/api/address/1")
                            .contentType(MediaType.APPLICATION_JSON));

            mockMvc.perform(delete("/api/address/2")
                            .contentType(MediaType.APPLICATION_JSON));

            mockMvc.perform(get("/api/subassembly/page/0")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent())
                    .andExpect(content().json("[]"));
        } catch (Exception error) {
            System.out.println("Exception during shouldDeleteOneSubassemblyAndCustomerAndAddresses test: " + error);
        }
    }

}
