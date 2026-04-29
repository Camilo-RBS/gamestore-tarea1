package com.pnc.gamestore.controllers;

import com.pnc.gamestore.model.Game;
import com.pnc.gamestore.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("game")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public ResponseEntity<List<Game>> getAll(){
        return ResponseEntity.ok(gameService.getAll());
    }

    @GetMapping("/genre")
    public ResponseEntity<List<Game>> getByGenre(@Param("genre") String genre){
        return ResponseEntity.ok(gameService.findByGenre(genre));
    }

    @PostMapping
    public ResponseEntity<Void> createGame(@RequestBody Game game) {
        gameService.createGame(game);
        return ResponseEntity.noContent().build();
    }

    @QueryMapping
    public List<Game> allGames() {
        return gameService.getAll();
    }

    @QueryMapping
    public Game gameById(@Argument UUID id) {
        return gameService.getById(id);
    }
}
