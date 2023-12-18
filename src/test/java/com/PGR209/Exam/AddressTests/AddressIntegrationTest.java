package com.PGR209.Exam.AddressTests;

import org.junit.jupiter.api.*;
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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddressIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    private static String dateTime;

    @BeforeAll
    public static void addressSetup() {
        dateTime = LocalDateTime.now().toString();
    }

    @Test
    @Order(1)
    public void shouldFetchFirstPageEmptyAddress() {
        try {
            mockMvc.perform(get("/api/address/page/0")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(204));
        } catch (Exception error) {
            System.out.println("Exception during shouldFetchFirstPageEmptyAddress test: " + error);
        }
    }

    @Test
    @Order(2)
    public void shouldCreateSingleAddress() {
        String requestBody = String.format("{\"addressName\":\"FirstAddress %s\"}", dateTime);

        try {
            mockMvc.perform(post("/api/address")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isCreated());
        } catch (Exception error) {
            System.out.println("Exception during shouldCreateSingleAddress test: " + error);
        }
    }

    @Test
    @Order(3)
    public void shouldFetchFirstAddress() {
        String expectedBody = String.format("{\"addressId\":1,\"addressName\":\"FirstAddress %s\",\"addressCustomers\":[]}", dateTime);

        try {
            mockMvc.perform(get("/api/address/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(expectedBody));
        } catch (Exception error) {
            System.out.println("Exception during shouldFetchFirstAddress test: " + error);
        }
    }

    @Test
    @Order(4)
    public void shouldUpdateAddress() {
        String newBody = "{\"addressName\":\"NewValue\"}";

        try {
            mockMvc.perform(put("/api/address/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(newBody))
                    .andExpect(status().isOk())
                    .andExpect(content().json(newBody));
        } catch (Exception error) {
            System.out.println("Exception during shouldUpdateAddress test: " + error);
        }
    }

    @Test
    @Order(5)
    public void shouldDeleteOneAddress() {
        try {
            mockMvc.perform(delete("/api/address/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

            mockMvc.perform(get("/api/address/page/0")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent())
                    .andExpect(content().json("[]"));
        } catch (Exception error) {
            System.out.println("Exception during shouldDeleteOneAddress test: " + error);
        }
    }
}
