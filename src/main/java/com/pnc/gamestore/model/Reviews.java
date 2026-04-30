package com.pnc.gamestore.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "reviews")
public class Reviews {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, name = "player")
    private String user;

    // no puede existir una review sin rating
    @Column(nullable = false)
    private Integer rating;

    @Column(length = 1000)
    private String comment;

    // La review pertenece a un juego.
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    public Reviews() {}

    public Reviews(String user, Integer rating, String comment, Game game) {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
        this.game = game;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getUser() { return user; }
    public void setUser(String user) { this.user = user; }

    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public Game getGame() { return game; }
    public void setGame(Game game) { this.game = game; }
}
