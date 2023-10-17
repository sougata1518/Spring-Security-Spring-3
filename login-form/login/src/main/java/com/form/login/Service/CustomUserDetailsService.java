package com.form.login.Service;

import com.form.login.Model.User;
import com.form.login.Repositary.UserRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositary userRepositary;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byEmail = userRepositary.findByEmail(username);
        System.out.println(byEmail.getName());
        if(byEmail == null){
            throw new UsernameNotFoundException("invalid");
        }
        return new CustomUserDetails(byEmail);
    }
}
