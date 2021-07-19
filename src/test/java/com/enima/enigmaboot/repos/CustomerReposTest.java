package com.enima.enigmaboot.repos;

import com.enima.enigmaboot.entities.Customers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerReposTest {

    @Autowired
    CustomerRepos customerRepos;

    @Autowired
    EntityManager entityManager;

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

    @Test
    void shouldSave_Customer(){
        Customers input = customerRepos.save(customers);
        assertNotNull(entityManager.find(Customers.class, input.getId()));
//        assertNotNull(entityManager.find(Customers.class, input.getFirstName()));
//        assertNotNull(entityManager.find(Customers.class, input.getLastName()));
//        assertNotNull(entityManager.find(Customers.class, input.getUserName()));
//        assertNotNull(entityManager.find(Customers.class, input.getPassword()));
//        assertNotNull(entityManager.find(Customers.class, input.getDateOfBirth()));
//        assertNotNull(entityManager.find(Customers.class, input.getStatus()));
    }

    @Test
    void shouldReturnCustomerBy_FirstNameAndLastName() {
    }

    @Test
    void findAll() {
    }

    @AfterEach
    void deleteAll(){
        customerRepos.deleteAll();
    }


}