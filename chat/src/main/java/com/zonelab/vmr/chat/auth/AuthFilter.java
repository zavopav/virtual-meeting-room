package com.zonelab.vmr.chat.auth;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            final HttpSession session = ((HttpServletRequest) request).getSession(true);
            final AuthContext ctx = (AuthContext) session.getAttribute(AuthContext.SESSION_ATTRIBUTE);
            if (ctx == null) {
                session.setAttribute(AuthContext.SESSION_ATTRIBUTE, AuthContext.GUEST);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
