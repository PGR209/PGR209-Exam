package com.PGR209.Exam.Subassembly;

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
public class SubassemblyIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void shouldFetchAllSubassemblies() {
        try {
            mockMvc.perform(get("/api/subassembly/page/0")
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(204));
        } catch (Exception error) {
            System.out.println("Exception during shouldFetchAllSubassemblies test: " + error);
        }
    }
}
