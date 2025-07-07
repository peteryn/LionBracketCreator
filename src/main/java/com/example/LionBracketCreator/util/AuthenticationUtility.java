package com.example.LionBracketCreator.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.UUID;

public class AuthenticationUtility {
    public static OAuth2AuthenticationToken getOAuthToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        return oauthToken;
    }

    public static String getProviderId(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        String provider = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
        OAuth2User oAuth2User = oAuth2AuthenticationToken.getPrincipal();
        String providerId;
        if (provider.equals("google")) {
            providerId = oAuth2User.getAttribute("sub");
        } else {
            providerId = oAuth2User.getAttribute("id").toString();
        }
        return providerId;
    }

    public static String getProvider(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        return oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
    }
}
