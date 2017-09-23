package com.zonelab.vmr.chat.controllers;

import com.zonelab.vmr.chat.auth.AuthContext;
import com.zonelab.vmr.chat.domain.Chat;
import com.zonelab.vmr.chat.domain.ChatId;
import com.zonelab.vmr.chat.repository.ChatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import static com.zonelab.vmr.chat.controllers.Response.fail;
import static com.zonelab.vmr.chat.controllers.Response.success;

@SessionAttributes(AuthContext.SESSION_ATTRIBUTE)
@RestController
@CrossOrigin
@RequestMapping(path = "/chat")
public class ChatController {
    private static final Logger log = LoggerFactory.getLogger(ChatController.class);
    private final ChatRepository chatRepository;
    private final ChatPermissionsService chatPermissions;

    @Autowired
    public ChatController(ChatRepository chatRepository, ChatPermissionsService chatPermissions) {
        this.chatRepository = chatRepository;
        this.chatPermissions = chatPermissions;
    }

    @GetMapping(path = "/")
    public Iterable<Chat> findAll(@SessionAttribute(AuthContext.SESSION_ATTRIBUTE) AuthContext ctx) {
        log.info("{}: findAll()", ctx);
        return chatRepository.findAll();
    }

    @GetMapping(path = "/{roomName}")
    public Iterable<Chat> findByRoomName(
            @SessionAttribute(AuthContext.SESSION_ATTRIBUTE) AuthContext ctx,
            @PathVariable("roomName") String roomName) {
        log.info("{}: findByRoomName('{}')", ctx, roomName);
        return chatRepository.findByRoomName(roomName);
    }

    @GetMapping(path = "/{roomName}/{chatName}")
    public Response findById(
            @SessionAttribute(AuthContext.SESSION_ATTRIBUTE) AuthContext ctx,
            @PathVariable("roomName") String roomName,
            @PathVariable("chatName") String name) {
        final ChatId chatId = ChatId.of(roomName, name);
        log.info("{}: findById('{}')", ctx, chatId);
        final Chat chat = chatRepository.findOne(chatId);
        if (chat == null) {
            final String details = "Not found: " + chatId;
            log.info("{}: {}", ctx, details);
            return fail(details);
        }
        if (!chatPermissions.canRead(ctx, chat)) {
            final String details = "No read permission: " + chatId;
            log.info("{}: {}", ctx, details);
            return fail(details);
        }
        return success(chat);
    }

    @PostMapping(path = "/create")
    public Response create(
            @SessionAttribute(AuthContext.SESSION_ATTRIBUTE) AuthContext ctx,
            @RequestBody Chat chat) {
        final ChatId chatId = chat.getChatId();
        log.info("{}: create('{}')", ctx, chatId);
        if (chatRepository.exists(chatId)) {
            final String details = "Already exists: " + chatId;
            log.info("{}: {}", ctx, details);
            return fail(details);
        }
        chat.setOwner(ctx.getUsername());
        return Response.success(chatRepository.save(chat));
    }

    @PostMapping(path = "/update")
    public Response update(
            @SessionAttribute(AuthContext.SESSION_ATTRIBUTE) AuthContext ctx,
            @RequestBody Chat chat) {
        final ChatId chatId = chat.getChatId();
        log.info("{}: update('{}')", ctx, chatId);
        if (!chatRepository.exists(chatId)) {
            final String details = "Not found: " + chatId;
            log.info("{}: {}", ctx, details);
            return fail(details);
        }
        if (!chatPermissions.canUpdate(ctx, chat)) {
            final String details = "No change permission: " + chatId;
            log.info("{}: {}", ctx, details);
            return fail(details);
        }
        return Response.success(chatRepository.save(chat));
    }

    @PostMapping(path = "/delete/{roomName}/{chatName}")
    public Response deleteById(
            @SessionAttribute(AuthContext.SESSION_ATTRIBUTE) AuthContext ctx,
            @PathVariable("roomName") String roomName,
            @PathVariable("chatName") String name) {
        final ChatId chatId = ChatId.of(roomName, name);
        log.info("{}: deleteById('{}')", ctx, chatId);
        final Chat chat = chatRepository.findOne(chatId);
        if (chat == null) {
            final String details = "Not found: " + chatId;
            log.info("{}: {}", ctx, details);
            return fail(details);
        }
        if (!chatPermissions.canDelete(ctx, chat)) {
            final String details = "No delete permission: " + chatId;
            log.info("{}: {}", ctx, details);
            return fail(details);
        }
        chatRepository.delete(chatId);
        return success(chat);
    }
}
