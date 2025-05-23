package com.example.LionBracketCreator.services;

import com.example.LionBracketCreator.domain.UserEntity;
import com.example.LionBracketCreator.repositories.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oauthUser = oauthToken.getPrincipal();
        String provider = oauthToken.getAuthorizedClientRegistrationId();
        String name = oauthUser.getAttribute("name");
        String toBeHashed = provider + ":" + name;
        System.out.println("to be hashed: " + toBeHashed);
        UserEntity userEntity = new UserEntity(toBeHashed, name);
        userRepository.save(userEntity);

        response.sendRedirect("/");
    }
}
