package com.zonelab.wbd.chat.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Chat {
    @Id
    private String id;
    private final String name;
}
