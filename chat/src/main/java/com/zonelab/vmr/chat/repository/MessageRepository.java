package com.zonelab.vmr.chat.repository;

import com.zonelab.vmr.chat.domain.ChatId;
import com.zonelab.vmr.chat.domain.Message;
import com.zonelab.vmr.chat.domain.MessageId;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.Repository;

import java.util.List;

@EnableScan
public interface MessageRepository extends Repository<Message, MessageId> {
    List<Message> findByChatId(ChatId chatId);
    Message save(Message message);
}
