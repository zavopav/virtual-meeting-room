package com.zonelab.vmr.chat.domain;

import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class ChatRepository {

    private final ReactiveMongoTemplate template;

    @Autowired
    public ChatRepository(ReactiveMongoTemplate template) {
        this.template = template;
    }

    public Mono<Chat> findById(String id) {
        return template.findById(id, Chat.class);
    }

    public Flux<Chat> findAll() {
        return template.findAll(Chat.class);
    }

    public Mono<Chat> save(Mono<Chat> chat) {
        return template.insert(chat);
    }

    public Mono<DeleteResult> deleteById(String id) {
        return template.remove(Query.query(Criteria.where("_id").is(id)), Chat.class);
    }
}
