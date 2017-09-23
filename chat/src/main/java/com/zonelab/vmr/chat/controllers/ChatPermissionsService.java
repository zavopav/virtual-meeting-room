package com.zonelab.vmr.chat.controllers;

import com.zonelab.vmr.chat.auth.AuthContext;
import com.zonelab.vmr.chat.domain.Chat;
import org.springframework.stereotype.Service;

@Service
class ChatPermissionsService {
    boolean canRead(AuthContext ctx, Chat chat) {
        if (chat.isPublic()) {
            return true;
        }
        return true;//todo: implement chat users
    }
    boolean canWrite(AuthContext ctx, Chat chat) {
        return canRead(ctx, chat);
    }

    boolean canUpdate(AuthContext ctx, Chat chat) {
        return chat.getOwner().equals(ctx.getUsername());
    }

    boolean canDelete(AuthContext ctx, Chat chat) {
        return canUpdate(ctx, chat);
    }
}
