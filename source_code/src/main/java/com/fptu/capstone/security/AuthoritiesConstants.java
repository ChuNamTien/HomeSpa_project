package com.fptu.capstone.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {

    public static final String ADMIN            = "ROLE_ADMIN";

    public static final String MODERATOR        = "ROLE_MODERATOR";

    public static final String CUSTOMER         = "ROLE_CUSTOMER";

    public static final String PARTNER          = "ROLE_PARTNER";

    public static final String PARTNER_STAFF    = "ROLE_PARTNER_STAFF";

    public static final String ANONYMOUS        = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {
    }
}
