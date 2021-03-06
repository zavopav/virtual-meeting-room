package com.zonelab.vmr.chat.controllers;

import com.zonelab.vmr.chat.domain.Message;
import com.zonelab.vmr.chat.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Controller
@CrossOrigin
public class WebSocketController {
    private final MessageRepository messageRepository;

    @Autowired
    public WebSocketController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @MessageMapping("/snapshot")
    @SendTo("/topic/snapshot")
    public List<Message> snapshot(Message message) {
        return messageRepository.findByChatId(message.getChatId());
    }

    @MessageMapping("/update")
    @SendTo("/topic/update")
    public Message update(Message message) {
        message.setTimestamp(System.currentTimeMillis());
        return messageRepository.save(message);
    }
}
