package com.pnc.gamestore.repositories;


import com.pnc.gamestore.model.Platforms;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PlatformsRepository extends JpaRepository<Platforms, UUID> {
}
