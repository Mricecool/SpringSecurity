package com.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.*;
import org.springframework.stereotype.Service;

/**
 * Created by app on 2017/2/16.
 */

public class ClientService implements UserDetailsService {

    private final ClientDetailsService clientDetailsService;
    private String emptyPassword = "";

    public ClientService(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.emptyPassword = passwordEncoder.encode("");
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ClientDetails clientDetails = this.clientDetailsService.loadClientByClientId(username);
        String clientSecret = clientDetails.getClientSecret();
        if(clientSecret == null || clientSecret.trim().length() == 0) {
            clientSecret = this.emptyPassword;
        }

        return new User(username, clientSecret, clientDetails.getAuthorities());
    }
}
