package com.PGR209.Exam.SalesOrder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SalesOrderIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldFetchAllSalesOrders() {
        try {
            mockMvc.perform(get("/api/salesorder")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception error) {
            System.out.println("Exception during shouldFetchAllSalesOrders test: " + error);
        }
    }
}