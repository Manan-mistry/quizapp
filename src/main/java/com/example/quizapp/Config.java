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
            URI dbUri = new URI("postgres://ozypvxwxfoqreo:4fe863838c01cfe701dd3d06a268897cb2abea0aa7f470545b26fadbdde7e09f@ec2-18-215-96-22.compute-1.amazonaws.com:5432/ddhmcc0r31sdfo");
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
