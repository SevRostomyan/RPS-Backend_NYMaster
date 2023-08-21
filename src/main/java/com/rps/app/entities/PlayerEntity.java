package com.rps.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//Lomboks annotationer för att automatiskt lägga till nedan komponenter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "player_entity")    //player class som motsvarar en tabell i databasen
public class PlayerEntity {


    @Id() //JPA (Java Persistence API) nyckel annotation
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "player_id") //JPA (Java Persistence API) kolumn annotation
    private UUID playerId; //id av typen uuid som autogenereras

    @Column(name = "player_name")
    private String playerName;
    
    @OneToMany(mappedBy = "playerOne", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<GameEntity> playerOneGames = new ArrayList<>();  //En variabel av typen GameEntity kopplad till PlayerEntity för att uppdatera GameEntity när det behövs

    @OneToMany(mappedBy = "playerTwo", cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private List<GameEntity> playerTwoGames = new ArrayList<>();  //En variabel av typen GameEntity kopplad till PlayerEntity för att uppdatera GameEntity när det behövs




    //Getters Setters
    public UUID getPlayerId() {
        return playerId;
    }   //Getter för playerId då Lomboks egna annotation verkade inte fungera som det ska

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }   //Setter för playerId då Lomboks egna annotation verkade inte fungera som det ska

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


    public List<GameEntity> getPlayerOneGames() {
        return playerOneGames;
    }

    public void setPlayerOneGames(List<GameEntity> playerOneGames) {
        this.playerOneGames = playerOneGames;
    }

    public List<GameEntity> getPlayerTwoGames() {
        return playerTwoGames;
    }

    public void setPlayerTwoGames(List<GameEntity> playerTwoGames) {
        this.playerTwoGames = playerTwoGames;
    }


    public void setPlayerOneGame(UUID fromString) {//Setter för att lägga till default in Player service i getUUid
    }

    public void setPlayerTwoGame(UUID fromString) {//För att lägga till default in Player service i getUUid
    }
}
