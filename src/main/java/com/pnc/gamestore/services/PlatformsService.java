package com.pnc.gamestore.services;

import com.pnc.gamestore.model.Platforms;
import com.pnc.gamestore.repositories.PlatformsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlatformsService {

    @Autowired
    private PlatformsRepository platformsRepository;

    public List<Platforms> getAll() {
        return platformsRepository.findAll();
    }

    public Platforms getById(UUID id) {
        return platformsRepository.findById(id).orElse(null);
    }

    public boolean createPlatform(Platforms platform) {
        // Regla: ninguna propiedad puede ser nula
        if (platform.getName() == null || platform.getName().isBlank()) return false;
        if (platform.getCompany() == null || platform.getCompany().isBlank()) return false;

        // Regla: no pueden existir plataformas duplicadas por nombre
        if (platformsRepository.findByName(platform.getName()).isPresent()) return false;

        platformsRepository.save(platform);
        return true;
    }

    public boolean updatePlatform(UUID id, Platforms updated) {
        Optional<Platforms> opt = platformsRepository.findById(id);
        if (opt.isEmpty()) return false;

        Platforms existing = opt.get();

        if (updated.getName() != null && !updated.getName().isBlank()) {
            // Verificar que el nuevo nombre no exista en otra plataforma
            Optional<Platforms> sameName = platformsRepository.findByName(updated.getName());
            if (sameName.isPresent() && !sameName.get().getId().equals(id)) return false;
            existing.setName(updated.getName());
        }

        if (updated.getCompany() != null && !updated.getCompany().isBlank()) {
            existing.setCompany(updated.getCompany());
        }

        platformsRepository.save(existing);
        return true;
    }

    public boolean deletePlatform(UUID id) {
        if (!platformsRepository.existsById(id)) return false;
        platformsRepository.deleteById(id);
        return true;
    }
}
