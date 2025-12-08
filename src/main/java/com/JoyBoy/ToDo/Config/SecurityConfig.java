package com.JoyBoy.ToDo.Config;

import java.beans.BeanProperty;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.JoyBoy.ToDo.Models.User;
import com.JoyBoy.ToDo.service.CustomUserDetailsService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;


import org.springframework.beans.factory.annotation.Autowired;


@Configuration
@EnableWebSecurity
public class SecurityConfig{

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
            http. 
                authorizeHttpRequests(auth ->auth
                    .requestMatchers("/","/tasks","/login","/register","/css/**","/js/**").permitAll()
                    .anyRequest().authenticated()
                )
                .formLogin(form -> form
                    .loginPage("/login")
                    .defaultSuccessUrl("/tasks")
                    .permitAll()
                )
                .logout(logout ->logout
                    .logoutSuccessUrl("/login?logout")
                    .permitAll()
                );

        return http.build();


    }

    @Bean
    public  PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public InMemoryUserDetailsManager UserDetailsService(){
    //     PasswordEncoder  encoder = new BCryptPasswordEncoder();
    //     UserDetailsService user = User.builders()
    //                                     .username(user.getUserName())
    //                                     .password(encoder.encode(user.getPassword()))
    //                                     .build();

    //     return new InMemoryUserDetailsManager(user);
    // }

    @Bean   
    public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService userDetailsService,PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);// takes the user object we created for spring through customUserDetails
        authProvider.setPasswordEncoder(passwordEncoder); // password encoder to compare the raw password with the stored password

        return authProvider;

        
    } 
}

