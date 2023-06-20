package com.brack.BrankBank.config;

import com.brack.BrankBank.model.Users;
import com.brack.BrankBank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrackbankUserDetails implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String userName, password = null;
        List<GrantedAuthority> authorities = null;
        List<Users> user = userRepository.findByEmail(username);
        if(user.size() == 0){
            throw new UsernameNotFoundException("User " + username + " could not be located.");
        } else {
            userName = user.get(0).getEmail();
            password = user.get(0).getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.get(0).getRole()));
        }

        return new User(username, password, authorities);
    }
}
