package com.example.security.config.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public class SecurityDaoImpl extends NamedParameterJdbcDaoSupport implements SecurityDao {

    private static final Logger logger = LoggerFactory.getLogger(SecurityDaoImpl.class);

    public static final String USER_NAME = "userName";
    public static final String USER_TYPE = "userType";
    public static final String BHP_NODE_ID = "bhpNodeId";


    static final String QUERY_FOR_USER_SQL = "SELECT username, user_type, is_ldap_user, is_system, disabled FROM Users WHERE username = :" + USER_NAME + " AND (user_type = :" + USER_TYPE + " OR :" + USER_TYPE + " IS NULL)";
    static final String QUERY_FOR_LICENSED_FEATURES_SQL = "SELECT name, description FROM CustomerFeature WHERE license_customer_name = (SELECT DISTINCT customer_name FROM CustomerLicense)";
    static final String QUERY_FOR_LICENSE_SQL = "SELECT customer_name, expiration_date, signature FROM CustomerLicense";
    static final String QUERY_FOR_BHP_ROOT_SQL = "SELECT bhp_node_id, feed_identifier, name, bhp_node_type FROM BhpNode WHERE parent_id IS NULL";
    static final String QUERY_FOR_BHP_ROOT_FEATURES = "SELECT cf.name, cf.description FROM CustomerFeature cf INNER JOIN BhpNodes_Features j ON cf.name = customer_feature_id WHERE j.bhp_node_id = :" + BHP_NODE_ID;
    static final String QUERY_FOR_SSO_ENABLE = "SELECT property_value from SystemProperty WHERE property_key = 'SSO_ENABLED'";
    static final String QUERY_FOR_SSO_SP_METADATA = "select updated_date, generated_sp_metadata from SsoConfiguration";
    static final String QUERY_FOR_SSO_IDP_METADATA = "select idp_metadata_uri from SsoConfiguration";
    static final String QUERY_FOR_KEY_STORE_CONFIGURATION = "select uri, password, type from KeyStoreConfiguration";
    static final String QUERY_FOR_USER_ROLE_GROUPS = "SELECT distinct 1 FROM Groups as g INNER JOIN User_Group as ug on ug.group_id = g.group_id" +
            " INNER JOIN UserCredentials uc on uc.user_credentials_id = ug.user_id INNER JOIN Users u on uc.user_id = u.user_id where g.obsolete = 0 and g.disabled = 0 and u.username = :" + USER_NAME;
    static final String NAMED_PARAMETER_EXCEPTION_MESSAGE = "NamedParameterJdbcTemplate cannot be null";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public SecurityDaoImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public SecurityUser findUserByUsername(final String username) {
        return findUserByUsernameAndType(username, null);
    }

    @Override
    public SecurityUser findUserByUsernameAndType(final String username, final UserType usertype) {


        try {
            return new SecurityUser("kishan", UserType.TRUCARE_INTERNAL,false,true,false);
        } catch (final EmptyResultDataAccessException exc) {
            logger.info("No user with username {} and type {} exists.", username, usertype, exc);
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean doesUserHaveAtLeastOneRoleGroup(final String username) {
        final NamedParameterJdbcTemplate namedTemplate = getNamedParameterJdbcTemplate();
        if (namedTemplate == null) {
            throw new IllegalStateException(NAMED_PARAMETER_EXCEPTION_MESSAGE);
        }

        final Map<String, String> namedParameters = new HashMap<>();
        namedParameters.put(USER_NAME, username);

        //return DataAccessUtils.singleResult(namedTemplate.queryForList(QUERY_FOR_USER_ROLE_GROUPS, namedParameters)) != null;
        return true;
    }


}