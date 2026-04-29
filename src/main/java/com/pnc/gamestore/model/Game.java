package com.pnc.gamestore.model;

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

    @Column(nullable = false)
    private String name;

    @Column
    private String genre;

    @Column
    private String classification;

    @Column(name = "game_developer")
    private String dev;

    @OneToOne(mappedBy = "game")
    private GameDetails details;

    @OneToMany(mappedBy = "game")
    private List<Reviews> reviews = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "game_platforms",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private List<Platforms> platforms = new ArrayList<>();

    public Game() {
    }

    public Game(String name, String genre, String classification, String dev) {
        this.name = name;
        this.genre = genre;
        this.classification = classification;
        this.dev = dev;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    public GameDetails getDetails() {
        return details;
    }

    public void setDetails(GameDetails details) {
        this.details = details;
    }

    public List<Reviews> getReviews() {
        return reviews;
    }

    public void setReviews(List<Reviews> reviews) {
        this.reviews = reviews;
    }

    public List<Platforms> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<Platforms> platforms) {
        this.platforms = platforms;
    }
}