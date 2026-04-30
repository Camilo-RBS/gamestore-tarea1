package com.pnc.gamestore.services;

import com.pnc.gamestore.model.Game;
import com.pnc.gamestore.model.Reviews;
import com.pnc.gamestore.repositories.GameRepository;
import com.pnc.gamestore.repositories.ReviewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewsService {

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Autowired
    private GameRepository gameRepository;

    public List<Reviews> getAll() {
        return reviewsRepository.findAll();
    }

    public List<Reviews> getByGameId(UUID gameId) {
        return reviewsRepository.findByGameId(gameId);
    }

    public Reviews getById(UUID id) {
        return reviewsRepository.findById(id).orElse(null);
    }

    public boolean createReview(UUID gameId, Reviews review) {
        // Regla: ninguna propiedad puede ser nula
        if (review.getUser() == null || review.getUser().isBlank()) return false;
        if (review.getRating() == null) return false;

        // El juego debe existir
        Optional<Game> gameOpt = gameRepository.findById(gameId);
        if (gameOpt.isEmpty()) return false;

        // Regla: no puede existir más de una review del mismo usuario para el mismo juego
        if (reviewsRepository.existsByUserAndGameId(review.getUser(), gameId)) return false;

        review.setGame(gameOpt.get());
        reviewsRepository.save(review);
        return true;
    }

    public boolean updateReview(UUID id, Reviews updated) {
        Optional<Reviews> opt = reviewsRepository.findById(id);
        if (opt.isEmpty()) return false;

        Reviews existing = opt.get();
        if (updated.getRating() != null) existing.setRating(updated.getRating());
        if (updated.getComment() != null) existing.setComment(updated.getComment());
        // No se permite cambiar el usuario ni el juego en un update

        reviewsRepository.save(existing);
        return true;
    }

    public boolean deleteReview(UUID id) {
        if (!reviewsRepository.existsById(id)) return false;
        reviewsRepository.deleteById(id);
        return true;
    }
}
