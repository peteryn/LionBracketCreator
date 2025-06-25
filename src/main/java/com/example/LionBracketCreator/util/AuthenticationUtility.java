package com.example.LionBracketCreator.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class AuthenticationUtility {
    public static String getLoggedInUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        return AuthenticationUtility.getIdString(oauthToken);
    }

    public static String getIdString(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        String provider = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
        OAuth2User oAuth2User = oAuth2AuthenticationToken.getPrincipal();
        String id;
        if (provider.equals("google")) {
            id = oAuth2User.getAttribute("sub");
        } else {
            id = oAuth2User.getAttribute("id").toString();
        }
        String uniqueId = provider + ":" + id;
        return uniqueId;
    }
}
