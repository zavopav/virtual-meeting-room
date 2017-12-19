package com.zonelab.vmr.auth.api;

import com.zonelab.vmr.auth.api.impl.AuthServiceImpl;

public final class AuthServiceFactory {
    public AuthService create() {
        return new AuthServiceImpl();
    }
}
