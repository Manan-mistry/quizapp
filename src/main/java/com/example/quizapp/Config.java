package com.example.quizapp;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;

@Configuration
public class Config {
        @Bean
        public DataSource getDataSource() throws URISyntaxException{
            URI dbUri = new URI(System.getenv("DATABASE_URL"));
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

            HikariDataSource source = new HikariDataSource();
            source.setDriverClassName("org.postgresql.Driver");
            source.setJdbcUrl(dbUrl);
            source.setUsername(username);
            source.setPassword(password);
            return source;

        }
}
