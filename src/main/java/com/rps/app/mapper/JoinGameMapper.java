package com.rps.app.mapper;

import com.rps.app.DTOs.GameResponseDTO;
import com.rps.app.entities.GameEntity;
import com.rps.app.entities.PlayerEntity;
import org.springframework.stereotype.Component;


@Component
public class JoinGameMapper {
    public GameResponseDTO mapToGameResponseDTO(GameEntity game) {
        GameResponseDTO gameResponseDTO = new GameResponseDTO();
        gameResponseDTO.setGameId(game.getGameId());
        PlayerEntity playerOne = game.getPlayerOne();
        PlayerEntity playerTwo = game.getPlayerTwo();
        if (playerOne != null) {
            gameResponseDTO.setPlayerOne(playerOne.getPlayerId().toString());
            gameResponseDTO.setPlayerTwo( playerTwo.getPlayerId().toString());
        }
        gameResponseDTO.setStatus(game.getStatus() != null ? game.getStatus().toString() : "Unknown Status");
        return gameResponseDTO;
    }
}