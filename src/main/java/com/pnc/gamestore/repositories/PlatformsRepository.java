package com.pnc.gamestore.repositories;

import com.pnc.gamestore.model.Platforms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PlatformsRepository extends JpaRepository<Platforms, UUID> {

    //para evitar duplicados
    Optional<Platforms> findByName(String name);

    // Buscar plataformas por compañía
    java.util.List<Platforms> findByCompanyIgnoreCase(String company);
}
