package com.example.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.JdbcUserDetailsManagerConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class AppConfiguration {
//    @Bean
//    public UserDetailsManager userDetailsManager() {
//        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//        userDetailsManager.createUser(
//                User.withUsername("tuhucon")
//                    .password("tuhucon")
//                    .authorities("ROLE_USER")
//                    .build()
//        );
//        userDetailsManager.createUser(
//                User.withUsername("chichchoe")
//                    .password("chichchoe")
//                    .authorities("ROLE_USER")
//                    .build()
//        );
//        return userDetailsManager;
//    }


//    @Bean
//    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
//        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager();
//        userDetailsManager.setDataSource(dataSource);
//        userDetailsManager.setEnableGroups(true);
//        return  userDetailsManager;
//    }


//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}
