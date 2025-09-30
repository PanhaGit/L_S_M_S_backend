package com.team_yerng.l_s_m_s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LSMSApplication {

    public static void main(String[] args) {
        io.github.cdimascio.dotenv.Dotenv dotenv = io.github.cdimascio.dotenv.Dotenv.configure()
                .ignoreIfMissing()
                .load();

        // Optional: populate System properties for Spring to read
        System.setProperty("jwt.secret", dotenv.get("JWT_SECRET"));
        System.setProperty("jwt.accessExpirationMs", dotenv.get("JWT_ACCESS_EXPIRATION_MS", "900000"));
        System.setProperty("jwt.refreshExpirationMs", dotenv.get("JWT_REFRESH_EXPIRATION_MS", "604800000"));

        SpringApplication.run(LSMSApplication.class, args);
    }
}
