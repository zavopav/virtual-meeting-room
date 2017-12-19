package com.zonelab.vmr.auth.api;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public final class AuthContext {
    public static final String SESSION_ATTRIBUTE = "auth.ctx";
    public static final AuthContext GUEST = new AuthContext("Guest");

    private final String username;

    @Override
    public String toString() {
        return username;
    }
}
