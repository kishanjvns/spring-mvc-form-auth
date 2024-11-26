
package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

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
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        LOG.info("Authenticating using CasenetAuthProvider...");
        LOG.info("userDetailService"+ userDetailsService);
        String userName = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails foundUser = userDetailsService.loadUserByUsername(userName);

        if (foundUser.getPassword().equals(password)) { // Use a password encoder for real applications
            return new UsernamePasswordAuthenticationToken(foundUser, password, foundUser.getAuthorities());
        } else {
            throw new BadCredentialsException("Invalid credentials");
        }
    }
}
