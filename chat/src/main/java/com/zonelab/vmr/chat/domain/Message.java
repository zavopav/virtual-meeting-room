package com.zonelab.vmr.chat.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@DynamoDBTable(tableName = "Message")
public class Message {
    @Id
    private MessageId messageId;

    @DynamoDBAttribute (attributeName = "Content")
    private String content;

    @DynamoDBAttribute (attributeName = "Author")
    private String author;

    @DynamoDBAttribute (attributeName = "Timestamp")
    private long timestamp;

    @DynamoDBHashKey(attributeName = "ChatId")
    @DynamoDBTypeConverted(converter = ChatIdConverter.class)
    public ChatId getChatId() {
        return messageId != null ? messageId.getChatId() : null;
    }

    public void setChatId(final ChatId chatId) {
        if (messageId == null) {
            messageId = new MessageId();
        }
        messageId.setChatId(chatId);
    }

    @DynamoDBRangeKey(attributeName = "Id")
    @DynamoDBAutoGeneratedKey
    public String getId() {
        return messageId != null ? messageId.getId() : null;
    }

    public void setId(final String id) {
        if (messageId == null) {
            messageId = new MessageId();
        }
        messageId.setId(id);
    }
}
