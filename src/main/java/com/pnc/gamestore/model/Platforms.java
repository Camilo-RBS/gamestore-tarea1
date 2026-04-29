package com.pnc.gamestore.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "platforms")
public class Platforms {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column
    private String company;

    @ManyToMany(mappedBy = "platforms")
    private List<Game> games = new ArrayList<>();

    public Platforms() {
    }

    public Platforms(String name, String company) {
        this.name = name;
        this.company = company;
    }
}
