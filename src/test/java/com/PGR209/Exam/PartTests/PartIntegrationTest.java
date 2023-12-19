package com.PGR209.Exam.PartTests;

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
public class PartIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    private static String dateTime;

    @BeforeAll
    public static void partSetup() {
        dateTime = LocalDateTime.now().toString();
    }

    @Test
    @Order(1)
    public void shouldFetchFirstPageEmptyPart() {
        try {
            mockMvc.perform(get("/api/part/page/0")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(204));
        } catch (Exception error) {
            System.out.println("Exception during shouldFetchFirstPageEmptyPart test: " + error);
        }
    }

    @Test
    @Order(2)
    public void shouldCreateSinglePart() {
        String requestBody = String.format("{\"partName\":\"FirstPart %s\"}", dateTime);

        try {
            mockMvc.perform(post("/api/part")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isCreated());
        } catch (Exception error) {
            System.out.println("Exception during shouldCreateSinglePart test: " + error);
        }
    }

    @Test
    @Order(3)
    public void shouldFetchFirstPart() {
        String expectedBody = String.format("{\"partId\":1,\"partName\":\"FirstPart %s\"}", dateTime);

        try {
            mockMvc.perform(get("/api/part/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(expectedBody));
        } catch (Exception error) {
            System.out.println("Exception during shouldFetchFirstPart test: " + error);
        }
    }

    @Test
    @Order(4)
    public void shouldUpdatePart() {
        String newBody = "{\"partName\":\"NewValue\"}";

        try {
            mockMvc.perform(put("/api/part/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(newBody))
                    .andExpect(status().isOk())
                    .andExpect(content().json(newBody));
        } catch (Exception error) {
            System.out.println("Exception during shouldUpdatePart test: " + error);
        }
    }

    @Test
    @Order(5)
    public void shouldDeleteOnePart() {
        try {
            mockMvc.perform(delete("/api/part/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

            mockMvc.perform(get("/api/part/page/0")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent())
                    .andExpect(content().json("[]"));
        } catch (Exception error) {
            System.out.println("Exception during shouldDeleteOnePart test: " + error);
        }
    }
}
