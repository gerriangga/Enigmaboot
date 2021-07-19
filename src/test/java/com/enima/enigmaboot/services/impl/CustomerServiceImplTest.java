package com.enima.enigmaboot.services.impl;

import com.enima.enigmaboot.entities.Customers;
import com.enima.enigmaboot.repos.CustomerRepos;
import com.enima.enigmaboot.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CustomerServiceImplTest {

    @Autowired
    CustomerServiceImpl customerService;

    @MockBean
    CustomerRepos customerRepos;

    @Autowired
    MockMvc mockMvc;

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

        when(customerRepos.save(any())).thenReturn(customers);
    }

    @Test
    void saveCustomer() {
        customerService.saveCustomer(customers);

        List<Customers> customer = new ArrayList<>();
        customer.add(customers);

        when(customerRepos.findAll()).thenReturn(customer);
        assertEquals(1, customerRepos.findAll().size());
    }

    @Test
    void getCustomerById() {
//        customerRepos.save(customers);
//        Customers output = new Customers();
//        output.setId("c01");
//        output.setFirstName(customers.getFirstName());
//        output.setLastName(customers.getLastName());
//        output.setUserName(customers.getUserName());
//        output.setPassword(customers.getPassword());
//        output.setDateOfBirth(customers.getDateOfBirth());
//        output.setStatus(customers.getStatus());

//        given(customerRepos.findById("c01").willReturn(Optional))
    }

    @Test
    void getAllCustomers() {
        customerRepos.save(customers);
        List<Customers> customerX = new ArrayList<>();
        customerX.add(customers);

        when(customerService.getAllCustomers()).thenReturn(customerX);
        assertEquals(1, customerService.getAllCustomers().size());
    }

    @Test
    void deleteCustomer() {
        customerRepos.save(customers);
        customerService.deleteCustomer("c01");
        assertEquals(0,customerRepos.findAll().size());
    }

    @Test
    void getCustomerPerPage() {
    }

    @Test
    void getCustomerByName() {
    }
}