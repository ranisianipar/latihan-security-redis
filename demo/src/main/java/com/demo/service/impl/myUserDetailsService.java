package com.demo.service.impl;

import com.demo.entity.User;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class myUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown User");
        }
//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNotExpired = true;
//        boolean accountNonLocked = true;
//
//        User myUserDetails = new User(user.getUsername(), user.getPassword(),
//                enabled, accountNonExpired, credentialsNotExpired, accountNonLocked,
//                getAuthorities(user));
//        return myUserDetails;
        return user;
    }
//
//    private List<GrantedAuthority> getAuthorities(User user){ // untuk mendapatkan rolenya
//        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
//        setAuths.add(new SimpleGrantedAuthority(user.getRole().toString()));
//        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
//        return Result;
//    }


}