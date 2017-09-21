package com.zonelab.vmr.chat.controllers;

import com.zonelab.vmr.chat.auth.SessionContext;
import com.zonelab.vmr.chat.domain.User;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
public class LoginController {
    @GetMapping(path = "/login")
    public boolean login(final HttpSession session, final HttpServletRequest request){
        final String username = request.getParameter("username");
        final String password = request.getParameter("password");
        final User user = fetchUser(username, password);

        if (user == null) {
            return false;
        }
        final SessionContext ctx = (SessionContext) session.getAttribute(SessionContext.ATTRIBUTE_NAME);
        ctx.setCurrentUser(user);
        return true;
    }

    private User fetchUser(final String username, final String password) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        return new User(username);
    }
}
