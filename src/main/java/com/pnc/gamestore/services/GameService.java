package com.pnc.gamestore.services;

import com.pnc.gamestore.model.Game;
import com.pnc.gamestore.model.Platforms;
import com.pnc.gamestore.model.enums.Classification;
import com.pnc.gamestore.repositories.GameRepository;
import com.pnc.gamestore.repositories.PlatformsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private PlatformsRepository platformsRepository;
    public List<Game> getAll() {
        return gameRepository.findAll();
    }
    public Game getById(UUID id) {
        return gameRepository.findById(id).orElse(null);
    }

    // CReacion
    public boolean createGame(Game game) {

        // Regla: ninguna propiedad puede ser nula
        if (game.getName() == null || game.getName().isBlank()) return false;
        if (game.getClassification() == null) return false;
        if (game.getGenre() == null || game.getGenre().isEmpty()) return false;
        if (game.getDev() == null || game.getDev().isBlank()) return false;

        // Regla: debe tener mínimo una plataforma
        if (game.getPlatforms() == null || game.getPlatforms().isEmpty()) return false;

        // Regla: no pueden existir 2 videojuegos con el mismo nombre
        if (gameRepository.findByName(game.getName()).isPresent()) return false;

        // Resolver plataformas: el body solo trae IDs, debemos cargar las entidades reales
        List<Platforms> resolvedPlatforms = platformsRepository.findAllById(
                game.getPlatforms().stream().map(Platforms::getId).toList()
        );
        if (resolvedPlatforms.isEmpty()) return false;

        // Regla: videojuegos con clasificación M no pueden estar en plataformas Nintendo
        if (game.getClassification() == Classification.M) {
            boolean hasNintendo = resolvedPlatforms.stream()
                    .anyMatch(p -> p.getCompany().equalsIgnoreCase("nintendo"));
            if (hasNintendo) return false;
        }

        game.setPlatforms(resolvedPlatforms);
        gameRepository.save(game);
        return true;
    }

// actualizar
    public boolean updateGame(UUID id, Game updated) {
        Optional<Game> opt = gameRepository.findById(id);
        if (opt.isEmpty()) return false;

        Game existing = opt.get();

        // Validar nombre único si cambi
        if (updated.getName() != null && !updated.getName().isBlank()) {
            Optional<Game> sameNameGame = gameRepository.findByName(updated.getName());
            // Si existe otro juego con ese nombre, rechazar
            if (sameNameGame.isPresent() && !sameNameGame.get().getId().equals(id)) return false;
            existing.setName(updated.getName());
        }

        if (updated.getClassification() != null) existing.setClassification(updated.getClassification());
        if (updated.getGenre() != null && !updated.getGenre().isEmpty()) existing.setGenre(updated.getGenre());
        if (updated.getDev() != null && !updated.getDev().isBlank()) existing.setDev(updated.getDev());

        // Si actualizan plataformas
        if (updated.getPlatforms() != null && !updated.getPlatforms().isEmpty()) {
            List<Platforms> resolvedPlatforms = platformsRepository.findAllById(
                    updated.getPlatforms().stream().map(Platforms::getId).toList()
            );
            Classification cl = updated.getClassification() != null ? updated.getClassification() : existing.getClassification();
            if (cl == Classification.M) {
                boolean hasNintendo = resolvedPlatforms.stream()
                        .anyMatch(p -> p.getCompany().equalsIgnoreCase("nintendo"));
                if (hasNintendo) return false;
            }
            existing.setPlatforms(resolvedPlatforms);
        }

        gameRepository.save(existing);
        return true;
    }


    public boolean deleteGame(UUID id) {
        if (!gameRepository.existsById(id)) return false;
        gameRepository.deleteById(id);
        return true;
    }
}
