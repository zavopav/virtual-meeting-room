package com.zonelab.vmr.auth.api.impl;

import com.zonelab.vmr.auth.api.AuthContext;
import com.zonelab.vmr.auth.api.AuthException;
import com.zonelab.vmr.auth.api.AuthService;
import org.springframework.util.StringUtils;

public class AuthServiceImpl implements AuthService {
    @Override
    public AuthContext login(String username, String password) throws AuthException {
        if (StringUtils.isEmpty(username)) {
            throw new AuthException("Unknown user: " + username);
        }
        return new AuthContext(username);
    }
}
