package com.rps.app.mapper;

import com.rps.app.DTOs.GameResponseDTO;
import com.rps.app.entities.GameEntity;
import org.springframework.stereotype.Component;



/*@Component
public class GameInfoOchOpenGameMappertoDTO {
    public static GameResponseDTO mapToDTO(GameEntity game) {
        GameResponseDTO gameResponseDTO = new GameResponseDTO();
        gameResponseDTO.setId(game.getGameId());
        gameResponseDTO.setPlayerOne(game.getPlayerOne() != null ? game.getPlayerOne().getPlayerName() : "Unknown");
        gameResponseDTO.setPlayerTwo(game.getPlayerTwo() != null ? game.getPlayerTwo().getPlayerName() : "Unknown");
        gameResponseDTO.setStatus(game.getStatus() != null ? game.getStatus().toString() : "Unknown Status");
        gameResponseDTO.setPlayerOneMove(game.getPlayerOneMove() != null ? game.getPlayerOneMove().toString() : "Move not made yet");
        gameResponseDTO.setPlayerTwoMove(game.getPlayerTwoMove() != null ? game.getPlayerTwoMove().toString() : "Move not made yet");
        gameResponseDTO.setPlayerOneWins(game.getPlayerOneWins());
        gameResponseDTO.setPlayerTwoWins(game.getPlayerTwoWins());
        gameResponseDTO.setLastUpdated(game.getLastUpdated());
        return gameResponseDTO;
    }
}*/

@Component
public class GameInfoOchOpenGameMappertoDTO {
    public static GameResponseDTO mapToDTO(GameEntity game) {
        GameResponseDTO gameResponseDTO = new GameResponseDTO();
        gameResponseDTO.setGameId(game.getGameId());
        gameResponseDTO.setPlayerOne(game.getPlayerOne() != null ? game.getPlayerOne().getPlayerName() : "Unknown");
        gameResponseDTO.setPlayerTwo(game.getPlayerTwo() != null ? game.getPlayerTwo().getPlayerName() : "Unknown");
        gameResponseDTO.setStatus(game.getStatus() != null ? game.getStatus().toString() : "Unknown Status");

        // Only set the player moves in the response if both players have made their moves
        if (game.getPlayerOneMove() != null && game.getPlayerTwoMove() != null) {
            gameResponseDTO.setPlayerOneMove(game.getPlayerOneMove().toString());
            gameResponseDTO.setPlayerTwoMove(game.getPlayerTwoMove().toString());
        } else {
            gameResponseDTO.setPlayerOneMove(null);
            gameResponseDTO.setPlayerTwoMove(null);
        }

        gameResponseDTO.setPlayerOneWins(game.getPlayerOneWins());
        gameResponseDTO.setPlayerTwoWins(game.getPlayerTwoWins());
        gameResponseDTO.setLastUpdated(game.getLastUpdated());
        return gameResponseDTO;
    }
}
