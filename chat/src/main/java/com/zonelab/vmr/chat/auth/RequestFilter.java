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
public class RequestFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            final HttpSession session = ((HttpServletRequest) request).getSession(true);
            SessionContext ctx = (SessionContext) session.getAttribute(SessionContext.ATTRIBUTE_NAME);
            if (ctx == null) {
                ctx = SessionContext.GUEST;
                session.setAttribute(SessionContext.ATTRIBUTE_NAME, ctx);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
