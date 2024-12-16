package com.cdpo_spring_developer.service.service;


import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cdpo_spring_developer.service.entity.ApplicationUser;
import com.cdpo_spring_developer.service.repository.AccountRepository;

@Service
public class UserDetailService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public UserDetailService(AccountRepository applicationUserRepository) {
        this.accountRepository = applicationUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser applicationUser = accountRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("ApplicationUser not found"));

        GrantedAuthority authority = new SimpleGrantedAuthority(
                applicationUser.getUserRole().getRoleType().name());
        System.out.println(authority.getAuthority());

        return new User(username, applicationUser.getPassword(), Set.of(authority));
    }
}