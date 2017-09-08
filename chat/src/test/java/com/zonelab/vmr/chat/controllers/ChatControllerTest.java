package com.zonelab.vmr.chat.controllers;

import com.zonelab.vmr.chat.domain.Chat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChatControllerTest {
    private static final Logger log = LoggerFactory.getLogger(ChatControllerTest.class);

    private WebClient webClient;
    @LocalServerPort
    private int port;

    @Before
    public void setup() {
        this.webClient = WebClient.create("http://localhost:" + this.port);
    }

    @Test
    public void test() throws Exception {
        Chat created;
        {   //create
            final ClientResponse response = webClient.post()
                    .uri("/chat/create")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject("{\"name\":\"test chat\"}"))
                    .exchange().block();
            assertNotNull(response);
            assertEquals(HttpStatus.OK, response.statusCode());
            created = response.bodyToMono(Chat.class).block();
        }
        assertNotNull(created);
        {   // find
            final ClientResponse response = webClient.get()
                    .uri("/chat/" + created.getId())
                    .accept(MediaType.APPLICATION_JSON)
                    .exchange().block();
            assertNotNull(response);
            assertEquals(HttpStatus.OK, response.statusCode());
            final Chat chat = response.bodyToMono(Chat.class).block();
            assertEquals(created, chat);
        }
        {   // delete
            final ClientResponse response = webClient.post()
                    .uri("/chat/delete/" + created.getId())
                    .exchange().block();
            assertNotNull(response);
            assertEquals(HttpStatus.OK, response.statusCode());
            response.bodyToMono(Void.class).block();
        }
    }
}