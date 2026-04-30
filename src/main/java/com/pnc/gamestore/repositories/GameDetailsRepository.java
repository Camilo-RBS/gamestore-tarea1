package com.pnc.gamestore.repositories;

import com.pnc.gamestore.model.GameDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GameDetailsRepository extends JpaRepository<GameDetails, UUID> {

    // Buscar los detalles de un juego por el ID del juego
    Optional<GameDetails> findByGameId(UUID gameId);

    // Verificar si un juego ya tiene detalles (para evitar duplicados)
    boolean existsByGameId(UUID gameId);
}
