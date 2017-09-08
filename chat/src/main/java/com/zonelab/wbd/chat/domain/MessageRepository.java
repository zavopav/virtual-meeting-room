package com.zonelab.wbd.chat.domain;

import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Repository
public class MessageRepository {

    private final ReactiveMongoTemplate template;

    @Autowired
    public MessageRepository(ReactiveMongoTemplate template) {
        this.template = template;
    }

    public Flux<Message> findByChatId(String chatId) {
        return template.find(query(where("chatId").is(chatId)), Message.class);
    }

    public Mono<Message> save(Message message) {
        return template.insert(message);
    }

    public Mono<DeleteResult> deleteById(String id) {
        return template.remove(query(where("_id").is(id)), Message.class);
    }
}
