package com.example.security.config.auth;

public interface SecurityDao {

    /**
     * Find a user in the database regardless of user type.
     *
     * @param username
     *            the user's username
     * @return the matching {@link SecurityUser} object, or <code>null</code> if
     *         none found
     */
    SecurityUser findUserByUsername(String username);

    /**
     * Find a user in the database by both username and usertype. When usertype is null, just username will be used.
     *
     * @param username
     *            the user's username
     * @param usertype
     *            type of the user to be found (<code>null</code> for any)
     * @return the matching {@link SecurityUser} object, or <code>null</code> if
     *         none found
     */
    SecurityUser findUserByUsernameAndType(String username, UserType usertype);

    /**
     * Know if the user belongs to a role group
     *
     * @param username
     *            the user's username
     * @return false if the user has no role groups assigned, true otherwise
     */
    boolean doesUserHaveAtLeastOneRoleGroup (final String username);

    /**
     * Retrieve the {@link CustomerLicense} from the database.
     *
     * @return the license
     */
//    CustomerLicense getCustomerLicense();

    /**
     * Retrieve the BHP root node.
     *
     * @return the root {@link BhpNode}, or <code>null</code> if the BHP schema
     *         is unlocked
     */
//    BhpNode getBhpRoot();

    /**
     * Fetches the configured SSO ServiceProvider (SP) metadata.
     * @return SpMetadata/null (if not configured)
     */
//    SpMetadata getSSOServiceProviderMetadata();

    /**
     * Fetches the configured SSO IdentityProvider (IDP) metadata.
     * @return SpMetadata/null (if not configured)
     */
//    IdpMetadata getSSOIdentityProviderMetadata();

    /**
     * Fetches KeyStoreConfiguration information
     * @return KeyStoreConfiguration/null (if not configured)
     */
//    KeyStoreConfiguration getKeyStoreConfiguration();

}