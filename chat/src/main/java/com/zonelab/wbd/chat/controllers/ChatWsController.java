package com.zonelab.wbd.chat.controllers;

import com.zonelab.wbd.chat.domain.Message;
import com.zonelab.wbd.chat.domain.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@CrossOrigin
public class ChatWsController {
    private final MessageRepository messageRepository;

    @Autowired
    public ChatWsController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @MessageMapping("/messages")
    @SendTo("/topic/messages")
    public List<Message> messages(Message message) {
        return messageRepository.findByChatId(message.getChatId()).collectList().block();
    }

    @MessageMapping("/add")
    @SendTo("/topic/add")
    public Message add(Message message) {
        message.setTimestamp(System.currentTimeMillis());
        return messageRepository.save(message).block();
    }
}
