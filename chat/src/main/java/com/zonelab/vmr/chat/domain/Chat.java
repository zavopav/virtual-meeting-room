package com.zonelab.vmr.chat.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@DynamoDBTable(tableName = "Chat")
public class Chat {
    @Id
    private ChatId chatId;
    private ChatType chatType = ChatType.PUBLIC;
    private String owner;

    @DynamoDBHashKey(attributeName = "RoomName")
    public String getRoomName() {
        return chatId != null ? chatId.getRoomName() : null;
    }

    public void setRoomName(String roomName) {
        if (chatId == null) {
            chatId = new ChatId();
        }
        this.chatId.setRoomName(roomName);
    }

    @DynamoDBRangeKey(attributeName = "Name")
    public String getName() {
        return chatId != null ? chatId.getName() : null;
    }

    public void setName(String name) {
        if (chatId == null) {
            chatId = new ChatId();
        }
        this.chatId.setName(name);
    }

    public boolean isPublic() {
        return chatType == ChatType.PUBLIC;
    }
}
