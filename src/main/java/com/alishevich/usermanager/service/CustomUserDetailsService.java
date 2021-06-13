package com.alishevich.usermanager.service;

import com.alishevich.usermanager.model.UserAccount;
import com.alishevich.usermanager.repository.UserAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserAccount user = repository.findByUserName(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user: " + userName);
        }

        return User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .disabled(user.getStatus().toString().equals("INACTIVE"))
                .build();
    }
}
