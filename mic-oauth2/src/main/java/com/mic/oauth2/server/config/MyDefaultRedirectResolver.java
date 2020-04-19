package com.mic.oauth2.server.config;

import org.springframework.security.oauth2.provider.endpoint.DefaultRedirectResolver;

public class MyDefaultRedirectResolver extends DefaultRedirectResolver {

    @Override
    protected boolean redirectMatches(String requestedRedirect, String redirectUri) {
        return true;
    }
}
