package com.pnc.gamestore.controllers;

import com.pnc.gamestore.model.Platforms;
import com.pnc.gamestore.services.PlatformsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/platform")
public class PlatformsController {

    @Autowired
    private PlatformsService platformsService;

    @GetMapping
    public ResponseEntity<List<Platforms>> getAll() {
        return ResponseEntity.ok(platformsService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Platforms> getById(@PathVariable UUID id) {
        Platforms p = platformsService.getById(id);
        if (p == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(p);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Platforms platform) {
        boolean success = platformsService.createPlatform(platform);
        if (!success) return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody Platforms platform) {
        boolean success = platformsService.updatePlatform(id, platform);
        if (!success) return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        boolean success = platformsService.deletePlatform(id);
        if (!success) return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }
}
