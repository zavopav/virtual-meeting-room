package com.zonelab.vmr.chat.auth;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("authService")
public class AuthService {

    public AuthContext login(final String username, final String password) throws AuthException {
        if (StringUtils.isEmpty(username)) {
            throw new AuthException("Unknown user: " + username);
        }
        return new AuthContext(username);
    }
}
