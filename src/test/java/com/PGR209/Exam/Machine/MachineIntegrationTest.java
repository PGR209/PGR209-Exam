package com.PGR209.Exam.Machine;

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
public class MachineIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    private static String dateTime;

    @BeforeAll
    public static void machineSetup() {
        dateTime = LocalDateTime.now().toString();
    }

    @Test
    @Order(1)
    public void shouldFetchFirstPageEmptyMachine() {
        try {
            mockMvc.perform(get("/api/machine/page/0")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(204));
        } catch (Exception error) {
            System.out.println("Exception during shouldFetchFirstPageEmptyMachine test: " + error);
        }
    }

    @Test
    @Order(2)
    public void shouldCreateSingleMachine() {
        String requestBody = String.format("{\"machineName\":\"FirstMachine %s\"}", dateTime);

        try {
            mockMvc.perform(post("/api/machine")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestBody))
                    .andExpect(status().isCreated());
        } catch (Exception error) {
            System.out.println("Exception during shouldCreateSingleMachine test: " + error);
        }
    }

    @Test
    @Order(3)
    public void shouldFetchFirstMachine() {
        String expectedBody = String.format("{\"machineId\":1,\"machineName\":\"FirstMachine %s\",\"machineQuantity\":0,\"machineSubassemblies\":[]}", dateTime);

        try {
            mockMvc.perform(get("/api/machine/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(expectedBody));
        } catch (Exception error) {
            System.out.println("Exception during shouldFetchFirstMachine test: " + error);
        }
    }

    @Test
    @Order(4)
    public void shouldUpdateMachine() {
        String newBody = "{\"machineName\":\"NewValue\"}";

        try {
            mockMvc.perform(put("/api/machine/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(newBody))
                    .andExpect(status().isOk())
                    .andExpect(content().json(newBody));
        } catch (Exception error) {
            System.out.println("Exception during shouldUpdateMachine test: " + error);
        }
    }

    @Test
    @Order(5)
    public void shouldDeleteOneMachine() {
        try {
            mockMvc.perform(delete("/api/machine/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

            mockMvc.perform(get("/api/machine/page/0")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent())
                    .andExpect(content().json("[]"));
        } catch (Exception error) {
            System.out.println("Exception during shouldDeleteOneMachine test: " + error);
        }
    }

}
