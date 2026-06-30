package com.info.spring.dar.springboot_aplicacion.service;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Custom implementation of Spring Security's UserDetails interface.
 *
 * Wraps the authenticated user's credentials and authorities so they
 * can be used by the Spring Security framework during authentication and
 * authorization. Also stores the user's full name for display in the UI.
 */
public class CustomUserDetails implements UserDetails {

    /** Email address used as the username for authentication. */
    private String email;

    /** BCrypt-encoded password. */
    private String password;

    /** Display name of the user, shown in the navbar. */
    private String fullName;

    /** List of granted roles/authorities for this user. */
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Full constructor.
     *
     * @param email       the user's email (used as username)
     * @param password    the BCrypt-encoded password
     * @param fullName    the user's display name
     * @param authorities the list of roles granted to this user
     */
    public CustomUserDetails(String email, String password, String fullName,
            Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.authorities = authorities;
    }

    /**
     * Returns the display name of the authenticated user.
     *
     * @return the user's full name
     */
    public String getFullName() {
        return fullName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    /** Returns the email as the username used for authentication. */
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}