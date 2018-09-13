package com.axeane.SpringBootHikaricp.controllers;

import com.axeane.SpringBootHikaricp.SpringBootHikaricpApplication;
import com.axeane.SpringBootHikaricp.model.Salarie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootHikaricpApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SalariesControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void addSalaries() {
        ResponseEntity<Salarie> responseEntity = restTemplate.postForEntity("/salaries", new Salarie("ilyes", "raissi", new BigDecimal(444444), "Tunis"), Salarie.class);
        Salarie salarie = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assert salarie != null;
        assertEquals("ilyes", salarie.getNom());
    }

}