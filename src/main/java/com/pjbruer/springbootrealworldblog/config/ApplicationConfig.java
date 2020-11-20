package com.pjbruer.springbootrealworldblog.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

// TODO - utilize environment variables?
// TODO - @EnableRetry? - Spring retry feature

@Configuration
public class ApplicationConfig {

    @Profile("local")
    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .username("root")
                .password("password")
                .url("jdbc:mysql://localhost:3306/blog_db?createDatabaseIfNotExist=true&useSSL=false")
                .build();
    }

}
