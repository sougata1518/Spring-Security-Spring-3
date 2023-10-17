package com.form.login.Repositary;

import com.form.login.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositary extends JpaRepository<User,Integer> {

    User findByEmail(String email);
}
