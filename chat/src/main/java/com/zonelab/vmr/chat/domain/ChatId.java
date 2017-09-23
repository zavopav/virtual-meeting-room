package com.zonelab.vmr.chat.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class ChatId implements Serializable {
    private static final char DELIMITER = '#';
    private static final long serialVersionUID = 1L;

    @DynamoDBHashKey
    private String roomName;

    @DynamoDBRangeKey
    private String name;

    @Override
    public String toString() {
        return roomName + DELIMITER + name;
    }

    public static ChatId fromString(final String str) {
        if (str == null) {
            return null;
        }

        final ChatId chatId = new ChatId();
        final int delimiter = str.indexOf(DELIMITER);

        if (delimiter == -1) {
            chatId.setRoomName(str);
        } else {
            chatId.setRoomName(str.substring(0, delimiter));
            chatId.setName(str.substring(delimiter + 1));
        }
        return chatId;
    }

    public static ChatId of(final String roomName, final String name) {
        final ChatId chatId = new ChatId();
        chatId.setRoomName(roomName);
        chatId.setName(name);
        return chatId;
    }
}
