package com.pnc.gamestore.model;

import com.pnc.gamestore.model.enums.Classification;
import com.pnc.gamestore.model.enums.Genre;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "video_games")
public class Game {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // nullable = false: la BD rechaza nulos.
    @Column(nullable = false, unique = true)
    private String name;

    // Un juego puede tener MULTIPLES géneros
    @ElementCollection
    @CollectionTable(name = "game_genres", joinColumns = @JoinColumn(name = "game_id"))
    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private List<Genre> genre = new ArrayList<>();

    // Un juego tiene UNA sola clasificación
    @Column
    @Enumerated(EnumType.STRING)
    private Classification classification;

    @Column(name = "game_developer")
    private String dev;

    @OneToOne(mappedBy = "game", cascade = CascadeType.ALL)
    private GameDetails details;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Reviews> reviews = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "game_platforms",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private List<Platforms> platforms = new ArrayList<>();

    public Game() {}

    public Game(String name, List<Genre> genre, Classification classification, String dev) {
        this.name = name;
        this.genre = genre;
        this.classification = classification;
        this.dev = dev;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Genre> getGenre() { return genre; }
    public void setGenre(List<Genre> genre) { this.genre = genre; }

    public Classification getClassification() { return classification; }
    public void setClassification(Classification classification) { this.classification = classification; }

    public String getDev() { return dev; }
    public void setDev(String dev) { this.dev = dev; }

    public GameDetails getDetails() { return details; }
    public void setDetails(GameDetails details) { this.details = details; }

    public List<Reviews> getReviews() { return reviews; }
    public void setReviews(List<Reviews> reviews) { this.reviews = reviews; }

    public List<Platforms> getPlatforms() { return platforms; }
    public void setPlatforms(List<Platforms> platforms) { this.platforms = platforms; }
}
