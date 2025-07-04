package com.example.LionBracketCreator.services;

import com.example.LionBracketCreator.domain.UserEntity;
import com.example.LionBracketCreator.repositories.UserRepository;
import com.example.LionBracketCreator.util.AuthenticationUtility;
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
        OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User oauthUser = oAuth2AuthenticationToken.getPrincipal();

        String provider = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
        String providerId = AuthenticationUtility.getProviderId(oAuth2AuthenticationToken);

        if (!userRepository.existsByProviderAndProviderId(provider, providerId)) {
            String name = oauthUser.getAttribute("name");
            UserEntity userEntity = new UserEntity(name, provider, providerId);
            userRepository.save(userEntity);
        }

        response.sendRedirect("/");
    }
}
