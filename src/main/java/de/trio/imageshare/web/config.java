package de.trio.imageshare.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class config {
    @Configuration
    public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {

        @Override
        public void addResourceHandlers(final ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/upload/**").addResourceLocations("file://home/edward/IdeaProjects/Prog3/src/main/upload");
        }
    }
}
