
package com.example.security.config.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class CasenetAuthProvider implements AuthenticationProvider {

    private static final Logger LOG = LoggerFactory.getLogger(CasenetAuthProvider.class);

    //private final AuthenticationProvider ldapProvider;
   //private final AuthenticationProvider dbProvider;
    private final SecurityDao securityDao;
    private final boolean enforceUsernameCaseSensitivity;

    /*public CasenetAuthProvider(final AuthenticationProvider ldapProvider,
                               final AuthenticationProvider dbProvider, final SecurityDao securityDao,
                               final boolean enforceUsernameCaseSensitivity) {
        this.ldapProvider = ldapProvider;
        this.dbProvider = dbProvider;
        this.securityDao = securityDao;
        this.enforceUsernameCaseSensitivity = enforceUsernameCaseSensitivity;
    }*/
    public CasenetAuthProvider(final SecurityDao securityDao,
                               final boolean enforceUsernameCaseSensitivity) {
        this.securityDao = securityDao;
        this.enforceUsernameCaseSensitivity = enforceUsernameCaseSensitivity;
    }


    @Override
    public boolean supports(@SuppressWarnings("rawtypes") final Class authentication) {
        return true;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        LOG.info("Authenticating using CasenetAuthProvider...");
        // Implement authentication logic here
        return null;
    }
}
