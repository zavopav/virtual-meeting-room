package com.zonelab.vmr.chat;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ChatConfiguration implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(ChatConfiguration.class);
    private final GlobalProperties properties;

    public static void main(String[] args) {
        SpringApplication.run(ChatConfiguration.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        log.info("Starting with: {}", properties);
    }
}
