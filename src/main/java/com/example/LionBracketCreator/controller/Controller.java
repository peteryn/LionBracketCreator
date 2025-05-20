package com.example.LionBracketCreator.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/login/oauth2")
    public String login() {
        return "login";
    }

    @GetMapping("/userinfo")
    @ResponseBody
    public Map<String, Object> userInfo(@AuthenticationPrincipal OAuth2User oauth2User) {
        return oauth2User.getAttributes();
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

}
