package com.zonelab.wbd.chat.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Message {
    @Id
    private String id;
    private String chatId;
    private String content;
    private String author;
    private long timestamp;
}
