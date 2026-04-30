package com.pnc.gamestore;

import com.pnc.gamestore.model.Platforms;
import com.pnc.gamestore.repositories.PlatformsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class GamestoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(GamestoreApplication.class, args);
    }

    @Bean
    public CommandLineRunner seedPlatforms(PlatformsRepository platformsRepository) {
        return args -> {
            List<Platforms> initialPlatforms = List.of(
                    new Platforms("PlayStation 5", "Sony"),
                    new Platforms("Xbox Series X", "Microsoft"),
                    new Platforms("Nintendo Switch", "Nintendo"),
                    new Platforms("PC", "Various"),
                    new Platforms("Nintendo 3DS", "Nintendo")
            );

            for (Platforms platform : initialPlatforms) {
                boolean exists = platformsRepository.findByName(platform.getName()).isPresent();
                if (!exists) {
                    platformsRepository.save(platform);
                    System.out.println("✅ Plataforma insertada: " + platform.getName());
                }
            }
        };
    }
}