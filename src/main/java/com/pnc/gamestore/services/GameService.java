package com.pnc.gamestore.services;

import com.pnc.gamestore.model.Game;
import com.pnc.gamestore.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAll() {
        return gameRepository.findAll();
    }

    public List<Game> findByGenre(String genre) {
        return gameRepository.findByGenre(genre);
    }

    public void createGame(Game game) {
        gameRepository.save(game);
    }

    public Game getById(UUID id) {
        Optional<Game> game = gameRepository.findById(id);
        return game.isPresent() ? game.get() : null;
    }
}
