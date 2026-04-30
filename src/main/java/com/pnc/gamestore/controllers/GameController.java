package com.pnc.gamestore.controllers;

import com.pnc.gamestore.model.Game;
import com.pnc.gamestore.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

// @RestController = @Controller + @ResponseBody (responde JSON automáticamente)
// @RequestMapping define el prefijo de todas las rutas de este controlador
@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    // lista todos los juegos
    @GetMapping
    public ResponseEntity<List<Game>> getAll() {
        return ResponseEntity.ok(gameService.getAll());
    }

    // obtiene un juego por ID
    @GetMapping("/{id}")
    public ResponseEntity<Game> getById(@PathVariable UUID id) {
        Game game = gameService.getById(id);
        if (game == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(game);
    }

    // crea un nuevo juego
    //
    @PostMapping
    public ResponseEntity<Void> createGame(@RequestBody Game game) {
        boolean success = gameService.createGame(game);
        // Si la lógica de negocio falla → 400 con body vacío (como pide la tarea)
        if (!success) return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build(); // 204 No Content = éxito sin body
    }

    // actualiza un juego existente
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateGame(@PathVariable UUID id, @RequestBody Game game) {
        boolean success = gameService.updateGame(id, game);
        if (!success) return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }

    //  elimina un juego
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable UUID id) {
        boolean success = gameService.deleteGame(id);
        if (!success) return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }
}
