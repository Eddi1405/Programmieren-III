package de.trio.imageshare;

import de.trio.imageshare.web.config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(config.class)
public class ImageShareApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImageShareApplication.class, args);}

    }
