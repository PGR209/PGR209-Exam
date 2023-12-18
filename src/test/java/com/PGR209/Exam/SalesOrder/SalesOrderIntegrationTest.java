package com.PGR209.Exam.SalesOrder;

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
public class SalesOrderIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    private static String dateTime;

    @BeforeAll
    public static void salesOrderSetup() {
        dateTime = LocalDateTime.now().toString();
    }

    @Test
    @Order(1)
    public void shouldFetchFirstPageEmptySalesOrder() {
        try {
            mockMvc.perform(get("/api/salesorder/page/0")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().is(204));
        } catch (Exception error) {
            System.out.println("Exception during shouldFetchFirstPageEmptySalesOrder test: " + error);
        }
    }

    /*
    @Test
    @Order(2)
    public void shouldCreateSingleSalesOrderWithCustomerAndAddress() {
        //String customerBody = String.format("{\"customerName\":\"FirstCustomer %s\",\"customerEmail\":\"@test\"}", dateTime);
        //String addressBody = String.format("{\"addressName\":\"FirstAddress %s\"}", dateTime);
        String salesOrderBody = "{\"salesOrderCustomer\":{\"customerId\":1},\"salesOrderAddress\":{\"addressId\":1}}";

        try {
            mockMvc.perform(post("/api/customer")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(customerBody))
                    .andExpect(status().isCreated());

            mockMvc.perform(post("/api/address")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(addressBody))
                    .andExpect(status().isCreated());

            mockMvc.perform(post("/api/salesorder")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(salesOrderBody))
                    .andExpect(status().isCreated());
        } catch (Exception error) {
            System.out.println("Exception during shouldCreateSingleSalesOrderWithCustomerAndAddress test: " + error);
        }
    }

    @Test
    @Order(3)
    public void shouldFetchFirstSalesOrder() {
        String expectedBody = "{\"salesOrderId\":1}";

        try {
            mockMvc.perform(get("/api/salesorder/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().json(expectedBody));
        } catch (Exception error) {
            System.out.println("Exception during shouldFetchFirstSalesOrder test: " + error);
        }
    }

    @Test
    @Order(4)
    public void shouldUpdateSalesOrderAndCreateNewAddress() {
        String newAddressBody = String.format("{\"addressName\":\"SecondAddress %s\"}", dateTime);
        String newSalesOrderBody = "{\"salesOrderAddress\":{\"addressId\":2}}";

        try {
            mockMvc.perform(post("/api/address")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(newAddressBody))
                    .andExpect(status().isCreated());

            mockMvc.perform(put("/api/salesorder/1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(newSalesOrderBody))
                    .andExpect(status().isOk())
                    .andExpect(content().json(newSalesOrderBody));
        } catch (Exception error) {
            System.out.println("Exception during shouldUpdateSalesOrderAndCreateNewAddress test: " + error);
        }
    }

    @Test
    @Order(5)
    public void shouldDeleteOneSalesOrder() {
        try {
            mockMvc.perform(delete("/api/salesorder/1")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());

            mockMvc.perform(get("/api/salesorder/page/0")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNoContent())
                    .andExpect(content().json("[]"));
        } catch (Exception error) {
            System.out.println("Exception during shouldDeleteOneSalesOrder test: " + error);
        }
    }

     */
}
