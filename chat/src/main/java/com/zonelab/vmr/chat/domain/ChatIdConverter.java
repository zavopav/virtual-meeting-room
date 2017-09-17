package com.zonelab.vmr.chat.domain;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.util.Objects;

public class ChatIdConverter implements DynamoDBTypeConverter<String, ChatId> {

    @Override
    public String convert(final ChatId object) {
        return Objects.toString(object, null);
    }

    @Override
    public ChatId unconvert(final String object) {
        return ChatId.fromString(object);
    }
}