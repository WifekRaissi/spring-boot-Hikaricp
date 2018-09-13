package com.axeane.SpringBootHikaricp;

import javax.sql.DataSource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootHikaricpApplication implements CommandLineRunner {
    private final DataSource dataSource;

    public SpringBootHikaricpApplication(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SpringBootHikaricpApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DataSource = " + dataSource);
    }
}