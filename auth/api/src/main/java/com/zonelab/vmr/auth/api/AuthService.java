package com.zonelab.vmr.auth.api;

import org.springframework.stereotype.Service;

@Service("authService")
public interface AuthService {

    AuthContext login(final String username, final String password) throws AuthException;
}
