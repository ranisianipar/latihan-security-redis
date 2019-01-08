package com.demo.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserPrincipal extends User {
    private final Object user;

    public UserPrincipal(Object user, Collection<? extends GrantedAuthority> authorities) {
        super("user.getUsername()", "user.getPassword()", authorities);
        this.user = user;
    }

    public UserPrincipal(Object user, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super("user.getUsername()", "user.getPassword()", enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
    }

    public Object getUser() {
        return this.user;
    }
}
