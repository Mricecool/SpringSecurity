package com.security.service;

import com.security.model.Admin;
import com.security.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现客户端信息的查询（获取token）
 * Created by app on 2017/2/15.
 */
public class ClientDetailsService implements org.springframework.security.oauth2.provider.ClientDetailsService{

    @Autowired
    public LoginRepository loginRepository;

    public ClientDetailsService(){}

    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {

        if(loginRepository==null) {
            System.out.println("loginRepository is null");
        }

        Admin admin=loginRepository.findAdmin(s);
        if(admin==null){
            System.out.println("User not found");
            throw new NoSuchClientException("No client recognized with id: " + s);
        }else{

            List<String> authorizedGrantTypes = new ArrayList<String>();
            List<String> scopeGrantTypes = new ArrayList<String>();
            authorizedGrantTypes.add("password");
            authorizedGrantTypes.add("refresh_token");
            authorizedGrantTypes.add("client_credentials");

            scopeGrantTypes.add("read");
            scopeGrantTypes.add("write");
            scopeGrantTypes.add("trust");

            BaseClientDetails clientDetails = new BaseClientDetails();
            clientDetails.setClientId(admin.getuName());
            clientDetails.setClientSecret(admin.getPwd());
            clientDetails.setScope(scopeGrantTypes);
            clientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);
            clientDetails.setAuthorities(getGrantedAuthorities(admin));
            return clientDetails;
        }
    }
    private List<GrantedAuthority> getGrantedAuthorities(Admin user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getAuth()));
//        if(user.getAuth().equals("ADMIN")){
//            authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getAuth()));
//        }
        return authorities;
    }
}
