package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class InternalUserDetailService implements UserDetailsService {
//    @Autowired
//    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Object user = null;
        //         = userRepository.findByUsername(username);
//        if (user == null) {
//            throw new UsernameNotFoundException("Unknown User");
//        }
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNotExpired = true;
        boolean accountNonLocked = true;
        UserPrincipal principal = new UserPrincipal(
                user,
                enabled, accountNonExpired, credentialsNotExpired, accountNonLocked,
                getAuthorities(user.getRoles())
        );
        return principal;
    }
        return null;
    }
}
