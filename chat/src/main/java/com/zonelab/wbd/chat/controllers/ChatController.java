package com.zonelab.wbd.chat.controllers;

import com.zonelab.wbd.chat.domain.Chat;
import com.zonelab.wbd.chat.domain.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping(path = "/chat")
public class ChatController {
    private final ChatRepository chatRepository;

    @Autowired
    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @GetMapping(path = "/")
    public Flux<Chat> findAll() {
        return chatRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Mono<Chat> findById(@PathVariable(value = "id") String id) {
        return chatRepository.findById(id);
    }

    @PostMapping(path = "/create")
    public Mono<Chat> create(@RequestBody Mono<Chat> chat) {
        return chatRepository.save(chat);
    }

    @PostMapping(path = "/delete/{id}")
    public Mono<Void> deleteById(@PathVariable(value = "id") String id) {
        return chatRepository.deleteById(id).then();
    }
}
