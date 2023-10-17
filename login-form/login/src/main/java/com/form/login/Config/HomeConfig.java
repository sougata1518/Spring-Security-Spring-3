package com.form.login.Config;

import com.form.login.Service.CustomSuccessHandler;
import com.form.login.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class HomeConfig {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    CustomSuccessHandler customSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)throws Exception{

        httpSecurity.csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers("/register").permitAll();
                    auth.requestMatchers("/save").permitAll();
                    auth.requestMatchers("/admin-page").hasAuthority("ADMIN");
                    auth.requestMatchers("/user-page").hasAuthority("USER");
//                    auth.requestMatchers("/signin").authenticated();
                    auth.anyRequest().authenticated();
                })
                .formLogin(form->form.loginPage("/signin").loginProcessingUrl("/signin").successHandler(customSuccessHandler).permitAll())

                .logout(form->form.invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout").permitAll());
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
