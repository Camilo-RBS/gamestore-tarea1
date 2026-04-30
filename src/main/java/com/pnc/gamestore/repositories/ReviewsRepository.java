package com.pnc.gamestore.repositories;

import com.pnc.gamestore.model.Reviews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewsRepository extends JpaRepository<Reviews, UUID> {

    // Buscar todas las reviews de un juego específico por su UUID
    List<Reviews> findByGameId(UUID gameId);

    // Verificar si ya existe una review del mismo usuario para el mismo juego
    // Regla: no puede haber más de una review del mismo usuario para el mismo juego
    boolean existsByUserAndGameId(String user, UUID gameId);
}
