//package com.brack.BrankBank.config;
//
//
//import com.brack.BrankBank.model.Authority;
//import com.brack.BrankBank.model.Users;
//import com.brack.BrankBank.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//@Component
//public class BrackBankUsernamePasswordAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        List<Users> users = userRepository.findByEmail(username);
//        if(users.size() > 0){
//            if(passwordEncoder.matches(password, users.get(0).getPwd())){
//                return new UsernamePasswordAuthenticationToken(username, password, getGrantedAuthorities(users.get(0).getAuthorities()));
//            } else {
//                throw new BadCredentialsException("Incorrect credentials");
//            }
//        } else {
//            throw new BadCredentialsException("No user found");
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
//    }
//
//    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities){
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        for(Authority authority : authorities){
//            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
//        }
//
//        return grantedAuthorities;
//    }
//}
