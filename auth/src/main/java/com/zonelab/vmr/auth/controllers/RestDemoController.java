package com.zonelab.vmr.auth.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RestDemoController {

    @RequestMapping(value = "/", produces = "application/json")
    public Map<String, String> helloUser(Principal principal) {
        HashMap<String, String> result = new HashMap<String, String>();
        result.put("username", principal.getName());
        return result;
    }

    @RequestMapping(value = "/test", produces = "application/json")
    public String test() {
        return "test";
    }

    @RequestMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpSession session) {
        session.invalidate();
    }
}