package com.pnc.gamestore.services;

import com.pnc.gamestore.model.Game;
import com.pnc.gamestore.model.GameDetails;
import com.pnc.gamestore.repositories.GameDetailsRepository;
import com.pnc.gamestore.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameDetailsService {

    @Autowired
    private GameDetailsRepository gameDetailsRepository;

    @Autowired
    private GameRepository gameRepository;

    public List<GameDetails> getAll() {
        return gameDetailsRepository.findAll();
    }

    public GameDetails getById(UUID id) {
        return gameDetailsRepository.findById(id).orElse(null);
    }

    public GameDetails getByGameId(UUID gameId) {
        return gameDetailsRepository.findByGameId(gameId).orElse(null);
    }

    public boolean createDetails(UUID gameId, GameDetails details) {
        // Regla: ninguna propiedad puede ser nula
        if (details.getAbout() == null || details.getAbout().isBlank()) return false;
        if (details.getPublishYear() == null) return false;

        // Regla: publishYear debe estar entre 1975 y el año actual
        int currentYear = Year.now().getValue();
        if (details.getPublishYear() < 1975 || details.getPublishYear() > currentYear) return false;

        // El juego debe existir
        Optional<Game> gameOpt = gameRepository.findById(gameId);
        if (gameOpt.isEmpty()) return false;

        // Un juego no puede tener dos detalles
        if (gameDetailsRepository.existsByGameId(gameId)) return false;

        details.setGame(gameOpt.get());
        gameDetailsRepository.save(details);
        return true;
    }

    public boolean updateDetails(UUID id, GameDetails updated) {
        Optional<GameDetails> opt = gameDetailsRepository.findById(id);
        if (opt.isEmpty()) return false;

        GameDetails existing = opt.get();

        if (updated.getAbout() != null && !updated.getAbout().isBlank()) {
            existing.setAbout(updated.getAbout());
        }

        if (updated.getPublishYear() != null) {
            int currentYear = Year.now().getValue();
            if (updated.getPublishYear() < 1975 || updated.getPublishYear() > currentYear) return false;
            existing.setPublishYear(updated.getPublishYear());
        }

        gameDetailsRepository.save(existing);
        return true;
    }

    public boolean deleteDetails(UUID id) {
        if (!gameDetailsRepository.existsById(id)) return false;
        gameDetailsRepository.deleteById(id);
        return true;
    }
}
