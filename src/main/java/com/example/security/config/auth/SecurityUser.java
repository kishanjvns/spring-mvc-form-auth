package com.example.security.config.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUser {

    private final String username;
    private final UserType userType;
    private final boolean isLdapUser;
    private final boolean isSystemUser;
    private final boolean disabled;

    public SecurityUser(final String username, final UserType userType, final boolean isLdapUser, final boolean isSystemUser, final boolean disabled) {
        this.username = username;
        this.userType = userType;
        this.isLdapUser = isLdapUser;
        this.isSystemUser = isSystemUser;
        this.disabled = disabled;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof SecurityUser)) {
            return false;
        }
        final SecurityUser other = (SecurityUser) obj;
        if (getUsername() == null) {
            if (other.getUsername() != null) {
                return false;
            }
        } else if (!getUsername().equals(other.getUsername())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SecurityUser [username=" + getUsername() + ", userType=" + getUserType() + ", isLdapUser=" + getIsLdapUser() + ", isSystemUser="+ isSystemUser + ", disabled=" + disabled + "]";
    }

    public String getUsername() {
        return username;
    }

    public UserType getUserType() {
        return userType;
    }

    public boolean getIsLdapUser() {
        return isLdapUser;
    }

    public boolean getIsSystemUser() {
        return isSystemUser;
    }

    /**
     * Retrieves the username of the current authenticated user
     * @return username, null if there is no user or is anonymous
     */
    public static String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // In Spring Security 2.0.7, an un-authenticated connection returns null for
        // getAuthentication(). With Spring Security 3.1.4, an authentication for an
        // anonymous user is returned.
        //
        // If the connection is not authenticated, do not attempt to access the database
        // to look for the user name. Leave 'user' as null, and trust Spring Security to
        // properly protect the service call that this aspect is wrapping.

//        if (auth == null || CustomSpringSecurity3Service.isSpringAnonymousUser(auth))
        if (auth == null ){
            return null;
        } else {
            return auth.getName();
        }
    }
    public boolean isDisabled() {
        return disabled;
    }
}