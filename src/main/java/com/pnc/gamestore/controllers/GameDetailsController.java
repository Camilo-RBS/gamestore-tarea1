package com.pnc.gamestore.controllers;

import com.pnc.gamestore.model.GameDetails;
import com.pnc.gamestore.services.GameDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

// Los detalles también se anidan bajo
@RestController
@RequestMapping("/game/{gameId}/details")
public class GameDetailsController {

    @Autowired
    private GameDetailsService gameDetailsService;

    // detalles del juego
    @GetMapping
    public ResponseEntity<GameDetails> getByGameId(@PathVariable UUID gameId) {
        GameDetails d = gameDetailsService.getByGameId(gameId);
        if (d == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(d);
    }

    // crear detalles para ese juego
    @PostMapping
    public ResponseEntity<Void> create(@PathVariable UUID gameId, @RequestBody GameDetails details) {
        boolean success = gameDetailsService.createDetails(gameId, details);
        if (!success) return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }

    // actualizar detalles
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID gameId, @PathVariable UUID id, @RequestBody GameDetails details) {
        boolean success = gameDetailsService.updateDetails(id, details);
        if (!success) return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }

    // eliminar detalles
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID gameId, @PathVariable UUID id) {
        boolean success = gameDetailsService.deleteDetails(id);
        if (!success) return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }
}
