package com.pnc.gamestore.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "game_details")
public class GameDetails {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 2000, nullable = false)
    private String about;

    @Column(nullable = false)
    private Integer publishYear;

    // @OneToOne: un detalle pertenece a exactamente un juego
    @OneToOne
    @JoinColumn(name = "game_id", unique = true, nullable = false)
    private Game game;

    public GameDetails() {}

    public GameDetails(String about, Integer publishYear, Game game) {
        this.about = about;
        this.publishYear = publishYear;
        this.game = game;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getAbout() { return about; }
    public void setAbout(String about) { this.about = about; }

    public Integer getPublishYear() { return publishYear; }
    public void setPublishYear(Integer publishYear) { this.publishYear = publishYear; }

    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }
}
