package de.trio.imageshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ImageShareApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImageShareApplication.class, args);
        log.info("Execution Beginn");

    }

    }
