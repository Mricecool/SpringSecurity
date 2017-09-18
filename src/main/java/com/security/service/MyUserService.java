package com.security.service;

import com.security.model.Admin;
import com.security.repository.LoginRepository;
import com.security.repository.MainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现用户的查询（登录）
 * Created by app on 2016/9/2.
 */
@Service
public class MyUserService implements UserDetailsService {

    @Autowired
    public LoginRepository loginRepository;

    public void setLoginRepository(LoginRepository loginRepository){
        this.loginRepository=loginRepository;
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);
        if(loginRepository==null) {
            System.out.println("loginRepository is null");
        }

        Admin admin=loginRepository.findAdmin(s);
        if(admin==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
        System.out.println("name----"+admin.getuName());
        System.out.println("pwd-----"+admin.getPwd());
        return new User(admin.getuName(),admin.getPwd(),true,true,true,true,getGrantedAuthorities(admin));
    }

    private List<GrantedAuthority> getGrantedAuthorities(Admin user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        if(user.getAuth().equals("ADMIN")){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+user.getAuth()));
        }
        return authorities;
    }
}
