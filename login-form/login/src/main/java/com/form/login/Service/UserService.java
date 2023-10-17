package com.form.login.Service;

import com.form.login.Model.User;
import com.form.login.Repositary.UserRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepositary userRepositary;

    public void saveUserData(User user){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepositary.save(user);
    }
}
