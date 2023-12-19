package com.PGR209.Exam.SalesOrderTests;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SalesOrderIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldFetchFirstPageEmptySalesOrder() {
        try {
            mockMvc.perform(get("/api/salesorder/page/0")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(204));
        } catch (Exception error) {
            System.out.println("Exception during shouldFetchFirstPageEmptySalesOrder test: " + error);
        }
    }
}
