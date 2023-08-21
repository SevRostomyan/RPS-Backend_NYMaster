package com.rps.app.services;

import lombok.AllArgsConstructor;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


import com.rps.app.DTOs.PlayerIdDTO;
import com.rps.app.DTOs.GameResponseDTO;

import com.rps.app.mapper.GameInfoOchOpenGameMappertoDTO;
import com.rps.app.mapper.JoinGameMapper;

import com.rps.app.entities.GameEntity;
import com.rps.app.entities.Move;
import com.rps.app.entities.PlayerEntity;
import com.rps.app.entities.Status;

import com.rps.app.repository.GameRepository;
import com.rps.app.repository.PlayerRepository;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service //Markerar klassen som en service klass
@AllArgsConstructor //Konstruktör
public class GameService {
    private final GameRepository gameRepository;  //Kopplar GameService med GameStatusRepository för hantering av användardata
    private final PlayerRepository playerRepository;
    private final JoinGameMapper joinGameMapper;
    private static final Logger log = LoggerFactory.getLogger(GameService.class);


    //Metod att starta ett nytt spel

    public GameEntity startGame(PlayerIdDTO playerIdDTO) {
        UUID playerId = playerIdDTO.getPlayerId();
        System.out.println("Player Id received: " + playerId); // log playerId
        Optional<PlayerEntity> playerOptional = playerRepository.findById(playerId);
        if (playerOptional.isEmpty()) {
            throw new RuntimeException("Player not found for id: " + playerId); // add playerId to error message
        }
        PlayerEntity player = playerOptional.get();

        GameEntity game = new GameEntity();
        game.setStatus(Status.OPEN);
        game.setPlayerOne(player);

        try {
            game = gameRepository.save(game);
            System.out.println("Game created: " + game); // log created game
            player.getPlayerOneGames().add(game);
            playerRepository.save(player);
        } catch (Exception e) {
            System.out.println("Exception when creating game: " + e.getMessage()); // log the exception message
            throw new RuntimeException("Game couldn't be created", e);
        }

        return game;
    }


    //Få en lista med öppna spel mappad via GameResponseDTO och filtrerad på statusen OPEN
    public List<GameResponseDTO> getOpenGames() { //Metoden returnerar en lista innehållande objekt av klassen GameResponseDTO
        List<GameEntity> openGames = gameRepository.findByStatus(Status.OPEN); // Börja med att söka öppna spel och spara de i listobjektet openGames

        return openGames.stream()//Streamma igenom listan
                .map(GameInfoOchOpenGameMappertoDTO::mapToDTO)  //Mappa informationen i varje spel till GameResponseDTO
                .collect(Collectors.toList()); //Returnera de mappade spelen till en lista
    }


    //Metod för att komma in i ett öppet spel
    public ResponseEntity<GameResponseDTO> joinGame(UUID gameId, PlayerIdDTO playerIdDTO) {
        UUID playerId = playerIdDTO.getPlayerId();
        Optional<GameEntity> game = gameRepository.findById(gameId);

        if (game.isPresent()) {
            if (game.get().getStatus().equals(Status.OPEN)) {
                Optional<PlayerEntity> player = playerRepository.findById(playerId);
                if (player.isPresent()) {
                    game.get().setPlayerTwo(player.get());
                    player.get().setPlayerTwoGame(game.get().getGameId());
                    game.get().setStatus(Status.ACTIVE);
                    gameRepository.save(game.get());
                    playerRepository.save(player.get());
                    return new ResponseEntity<>(joinGameMapper.mapToGameResponseDTO(game.get()), HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Player not found
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST); // Game is not open
            }
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND); // Game not found
        }
    }



    //aktuella spelets information: id, status, vem som spelar och vilka drag de gör oavsett om spelare två är redan kopplad till spelet eller inte
    //Senaste 1.2:

    public GameResponseDTO getGameInfo(UUID gameId, PlayerIdDTO playerId) {
        GameEntity game = gameRepository.findById(gameId).orElse(null);  // Change this line to return null instead of throwing an exception



        if (game == null) {
            GameResponseDTO noGameResponseDTO = new GameResponseDTO();
            noGameResponseDTO.setMessage("No game found");  // Assuming your GameResponseDTO has a setMessage method
            return noGameResponseDTO;
        }



        // Fetch PlayerEntity based on PlayerId in GameEntity
        if (game.getPlayerOne() != null) {
            PlayerEntity playerOne = playerRepository.findById(game.getPlayerOne().getPlayerId()).orElse(null);
            game.setPlayerOne(playerOne);
        }
        if (game.getPlayerTwo() != null) {
            PlayerEntity playerTwo = playerRepository.findById(game.getPlayerTwo().getPlayerId()).orElse(null);
            game.setPlayerTwo(playerTwo);
        }
        System.out.println("Player one: " + game.getPlayerOne());
        System.out.println("Player two: " + game.getPlayerTwo());



        GameResponseDTO gameResponseDTO = GameInfoOchOpenGameMappertoDTO.mapToDTO(game);



        if (game.getPlayerOne() != null && game.getPlayerOne().getPlayerId().equals(playerId.getPlayerId())) {
            return gameResponseDTO;
        } else if (game.getPlayerTwo() != null && game.getPlayerTwo().getPlayerId().equals(playerId.getPlayerId())) {
            return gameResponseDTO;
        } else {
            throw new RuntimeException("Player not found in the game");
        }



    }



    //Final 1.2 (Fetching the game first

    public GameResponseDTO move(Move playerMove, PlayerIdDTO playerIdDto, UUID gameId) {
        UUID playerId = playerIdDto.getPlayerId();
        log.info("Player Move: {}, Player ID: {}, Game ID: {}", playerMove, playerId, gameId);

        if (playerId == null) {
            log.error("Player ID cannot be null");
            throw new IllegalArgumentException("Player ID cannot be null");
        }
        if (gameId == null) {
            log.error("Game ID cannot be null");
            throw new IllegalArgumentException("Game ID cannot be null");
        }

        log.info("Fetching game with ID: {}", gameId);
        GameEntity game = gameRepository.findById(gameId).orElseThrow(() -> {
            log.error("Game not found");
            return new EntityNotFoundException("Game not found");
        });

        // Fetch PlayerEntity based on PlayerId in GameEntity
        PlayerEntity player = null;
        if (game.getPlayerOne() != null && game.getPlayerOne().getPlayerId().equals(playerId)) {
            player = game.getPlayerOne();
        } else if (game.getPlayerTwo() != null && game.getPlayerTwo().getPlayerId().equals(playerId)) {
            player = game.getPlayerTwo();
        }

        if (player == null) {
            log.error("Player not found");
            throw new EntityNotFoundException("Player not found");
        }

        log.info("Fetched player: {}", player);
        log.info("Fetched game: {}", game);

        if (game.getPlayerOne().getPlayerId().equals(playerId)) {
            game.setPlayerOneMove(playerMove);
        } else if (game.getPlayerTwo().getPlayerId().equals(playerId)) {
            game.setPlayerTwoMove(playerMove);
        } else {
            throw new IllegalArgumentException("Player is not part of the game");
        }

        if (game.getPlayerOneMove() != null && game.getPlayerTwoMove() != null) {
            Move playerOneMove = game.getPlayerOneMove();
            Move playerTwoMove = game.getPlayerTwoMove();

            // Determine the game's outcome
            if ((playerOneMove == Move.ROCK && playerTwoMove == Move.SCISSORS) ||
                    (playerOneMove == Move.PAPER && playerTwoMove == Move.ROCK) ||
                    (playerOneMove == Move.SCISSORS && playerTwoMove == Move.PAPER)) {
                game.setStatus(Status.PLAYER_ONE_WIN);
            } else if (playerOneMove == playerTwoMove) {
                game.setStatus(Status.DRAW);
            } else {
                game.setStatus(Status.PLAYER_TWO_WIN);
            }

            // Update player wins
            int player1Wins = game.getPlayerOneWins();
            int player2Wins = game.getPlayerTwoWins();
            if (game.getStatus() == Status.PLAYER_ONE_WIN) {
                player1Wins++;
            } else if (game.getStatus() == Status.PLAYER_TWO_WIN) {
                player2Wins++;
            }
            game.setPlayerOneWins(player1Wins);
            game.setPlayerTwoWins(player2Wins);

            // Check for overall winner
            if (player1Wins == 3 || player2Wins == 3) {
                if (player1Wins > player2Wins) {
                    game.setStatus(Status.PLAYER_ONE_IS_THE_WINNER);
                    log.info("Player one wins the game by 3 - {} !", player2Wins);
                } else {
                    game.setStatus(Status.PLAYER_TWO_IS_THE_WINNER);
                    log.info("Player two wins the game by 3 - {} !", player1Wins);
                }
            }
            game.setPlayerOneMove(null);
            game.setPlayerTwoMove(null);
        }

        game.setLastUpdated(LocalDateTime.now());
        gameRepository.save(game);

        GameResponseDTO gameResponseDTO = GameInfoOchOpenGameMappertoDTO.mapToDTO(game);

        log.info("Game response: {}", gameResponseDTO);
        return gameResponseDTO;
    }


}