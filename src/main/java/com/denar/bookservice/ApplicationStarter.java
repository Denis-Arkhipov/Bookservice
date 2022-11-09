package com.denar.bookservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class ApplicationStarter {
    public static void main(String[] args) {
        log.info("Application start.");
        SpringApplication.run(ApplicationStarter.class, args);
        log.info("Application finished.");
    }
}
