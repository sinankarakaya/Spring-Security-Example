package com.sinan.security.service;


import com.sinan.security.model.CustomUserDetails;
import com.sinan.security.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final List<CustomUserDetails> users = Arrays.asList(
            new CustomUserDetails(new User("sinan.karakaya","123456","admin")),
            new CustomUserDetails(new User("merve.karakaya","123456","user"))
    );

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Supplier<UsernameNotFoundException> error =
                () -> new UsernameNotFoundException(
                        "Kullanıcı adı veya parola hatalı!");

        CustomUserDetails user = users.stream()
                .filter( item -> item.getUser().getUsername().equals(username))
                .findFirst()
                .orElseThrow(error);

        return user;
    }
}
