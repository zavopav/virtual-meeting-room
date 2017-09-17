package com.zonelab.vmr.chat.repository;

import com.zonelab.vmr.chat.domain.Chat;
import com.zonelab.vmr.chat.domain.ChatId;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ChatRepository extends CrudRepository<Chat, ChatId>{

}
