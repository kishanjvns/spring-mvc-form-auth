
package com.example.security.config.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

public class CasenetAuthProvider implements AuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(CasenetAuthProvider.class);

   ;
    private final boolean enforceUsernameCaseSensitivity;

    private final UserDetailsService userDetailsService;

    public CasenetAuthProvider(UserDetailsService userDetailsService,
                               final boolean enforceUsernameCaseSensitivity) {
        this.userDetailsService = userDetailsService;
        this.enforceUsernameCaseSensitivity = enforceUsernameCaseSensitivity;
    }


    @Override
    public boolean supports(@SuppressWarnings("rawtypes") final Class authentication) {
        return true;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        LOG.info("Authenticating using CasenetAuthProvider...");
        LOG.info("userDetailService"+ userDetailsService);
        return null;
    }
}
