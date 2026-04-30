package com.pnc.gamestore.controllers;

import com.pnc.gamestore.model.Reviews;
import com.pnc.gamestore.services.ReviewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

// Las reviews se anidan bajo
// Esto refleja que una review PERTENECE a un juego
@RestController
@RequestMapping("/game/{gameId}/review")
public class ReviewsController {

    @Autowired
    private ReviewsService reviewsService;

    // todas las reviews de un juego
    @GetMapping
    public ResponseEntity<List<Reviews>> getByGame(@PathVariable UUID gameId) {
        return ResponseEntity.ok(reviewsService.getByGameId(gameId));
    }

    //una review específica
    @GetMapping("/{id}")
    public ResponseEntity<Reviews> getById(@PathVariable UUID gameId, @PathVariable UUID id) {
        Reviews r = reviewsService.getById(id);
        if (r == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(r);
    }

    // crea una review para ese juego
    @PostMapping
    public ResponseEntity<Void> create(@PathVariable UUID gameId, @RequestBody Reviews review) {
        boolean success = reviewsService.createReview(gameId, review);
        if (!success) return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }

    // actualiza una review
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID gameId, @PathVariable UUID id, @RequestBody Reviews review) {
        boolean success = reviewsService.updateReview(id, review);
        if (!success) return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }

    //  elimina una review
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID gameId, @PathVariable UUID id) {
        boolean success = reviewsService.deleteReview(id);
        if (!success) return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }
}
