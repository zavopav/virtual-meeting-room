package com.zonelab.vmr.chat.controllers;

import com.zonelab.vmr.chat.auth.AuthContext;
import com.zonelab.vmr.chat.auth.AuthException;
import com.zonelab.vmr.chat.auth.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
public class LoginController {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    private final AuthService authService;

    @Autowired
    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/login")
    public boolean login(final HttpSession session, final HttpServletRequest request){
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        final AuthContext authContext;
        try {
            authContext = authService.login(username, password);
            session.setAttribute(AuthContext.SESSION_ATTRIBUTE, authContext);
        } catch (AuthException e) {
            log.error("Login error.", e);
            return false;
        }
        return true;
    }
}
