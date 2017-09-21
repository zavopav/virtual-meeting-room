package com.zonelab.vmr.chat.controllers;

import com.zonelab.vmr.chat.auth.SessionContext;
import com.zonelab.vmr.chat.domain.Chat;
import com.zonelab.vmr.chat.domain.ChatId;
import com.zonelab.vmr.chat.repository.ChatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes(SessionContext.ATTRIBUTE_NAME)
@RestController
@CrossOrigin
@RequestMapping(path = "/chat")
public class ChatController {
    private static final Logger log = LoggerFactory.getLogger(ChatController.class);
    private final ChatRepository chatRepository;

    @Autowired
    public ChatController(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    @GetMapping(path = "/")
    public Iterable<Chat> findAll(@SessionAttribute(SessionContext.ATTRIBUTE_NAME) SessionContext ctx) {
        log.info("Session: {}", ctx);
        return chatRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public Chat findById(@PathVariable(value = "id") String id) {
        return chatRepository.findOne(ChatId.fromString(id));
    }

    @PostMapping(path = "/create")
    public Chat create(@RequestBody Chat chat) {
        return chatRepository.save(chat);
    }

    @PostMapping(path = "/update")
    public Chat updateById(@RequestBody Chat chat) {
        return chatRepository.save(chat);
    }

    @PostMapping(path = "/delete/{id}")
    public void deleteById(@PathVariable(value = "id") String id) {
        chatRepository.delete(ChatId.fromString(id));
    }
}
