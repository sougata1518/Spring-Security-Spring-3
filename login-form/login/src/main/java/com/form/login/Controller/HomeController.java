package com.form.login.Controller;

import com.form.login.Model.User;
import com.form.login.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String signup(){
        System.out.println("reg");
        return "signup";
    }

    @GetMapping("/signin")
    public String in(){
        System.out.println("log");
        return "login";
    }

    @PostMapping("/save")
    public String saveData(@ModelAttribute User user){
        userService.saveUserData(user);
        return "redirect:/register?success";
    }

    @GetMapping("/user-page")
    public String userPage(Model model, Principal principal){
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user",userDetails);
        return "user";
    }

    @GetMapping("/admin-page")
    public String adminPage(Model model, Principal principal){
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user",userDetails);
        return "admin";
    }
}
