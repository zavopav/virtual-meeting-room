package com.zonelab.vmr.chat.auth;

import com.zonelab.vmr.chat.domain.User;
import lombok.Data;

@Data
public final class SessionContext {
    public static final String ATTRIBUTE_NAME = "session.context";
    public static final SessionContext GUEST;
    static {
        GUEST = new SessionContext();
        GUEST.setCurrentUser(new User("Guest"));
    }

    private User currentUser;
}
