package com.football_championship_api.demo.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class CustomDataSource {

    private static final Dotenv dotenv = Dotenv.configure().load();

    private static final String URL = dotenv.get("DB_URL");
    private static final String USER = dotenv.get("DB_USER"); // Assure-toi que ta clé est bien DB_USER dans .env
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données.");
            e.printStackTrace();
            return null;
        }
    }
}
