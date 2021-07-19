package com.enima.enigmaboot.controllers;

import com.enima.enigmaboot.constant.ApiUrlConstant;
import com.enima.enigmaboot.entities.Customers;
import com.enima.enigmaboot.services.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class) //-> wajib for controller
class CustomerControllerTest {

    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    private Customers customers;

    @BeforeEach
    void setup(){
        customers = new Customers();
        customers.setId("c01");
        customers.setFirstName("xyz");
        customers.setLastName("qwe");
        customers.setUserName("asdfgh");
        customers.setPassword("asdfgh");
        customers.setDateOfBirth(new Date());
        customers.setStatus(1);
    }

    //ObjectMapper berfungsi untuk mengkonversi yang awalnya dalam bentuk object menjadi string dan sebaliknya
    public static String aJsonString(final Object obj){
        try{
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    @Test
    void shouldSave_CustomerSuccess() throws Exception{
        //actual
        when(customerService.saveCustomer(any(Customers.class))).thenReturn(customers);

        //tanpa object mapper
        RequestBuilder request = post(ApiUrlConstant.CUSTOMER)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\": " + "\"c01\"" + ", \"firstName\": " + "\"xyz\"" + ", \"lastName\": " + "\"qwe\"" + ", \"userName\": "
                        + "\"asdfgh\"" + ", \"password\": " + "\"asdfgh\"" + ", \"dateOfBirth\": " + "\"2021-07-16\"" + ", \"status\": "
                        + customers.getStatus() + "}");

        //expected
        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.message", Matchers.is("Customer data has been stored")))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.payLoad.userName", Matchers.is(customers.getUserName())));
    }

    @Test
    void getCustomersById_Success() throws Exception{
        when(customerService.getCustomerById("c01")).thenReturn(customers);

        RequestBuilder requestBuilder = get("/api/customers/c01").contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userName", Matchers.is(customers.getUserName())));
    }

    @Test
    void updateCustomer() throws Exception{
        given(customerService.saveCustomer(any(Customers.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        mockMvc.perform(put("/api/customers")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(aJsonString(customers)).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect( jsonPath("$.userName", Matchers.is(customers.getUserName())));
    }

    @Test
    void deleteCustomerById_Success() throws Exception {
        String customerId = "yaphets";
        Customers newCustomer = new Customers(customerId, "xyz", "xyz", new Date(), "asdasd", "qwe", 1);
        given(customerService.getCustomerById(customerId)).willReturn(newCustomer);

        //testing function void dari mockito menggunakan doNothing, doThrow, doAnswer -> melakukan testing pada void
        //actual
        doNothing().when(customerService).deleteCustomer(newCustomer.getId());

        //expected
        mockMvc.perform(delete("/api/customers/{id}", newCustomer.getId()))
                .andExpect(status().isOk());
    }

    @Test
    void searchCustomerPerPage() {
    }
}