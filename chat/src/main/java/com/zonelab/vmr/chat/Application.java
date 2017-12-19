package com.zonelab.vmr.chat;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.zonelab.vmr.chat")
@SpringBootApplication
@RequiredArgsConstructor
public class ApplicationConfiguration implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(ApplicationConfiguration.class);
    private final ApplicationProperties properties;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfiguration.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        log.info("Starting with: {}", properties);
    }
}
