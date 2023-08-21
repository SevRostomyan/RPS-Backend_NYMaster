package com.rps.app.controller;

import com.rps.app.DTOs.MoveRequestDTO;
import com.rps.app.DTOs.GameResponseDTO;
import com.rps.app.DTOs.PlayerIdDTO;
import com.rps.app.entities.GameEntity;

import com.rps.app.services.GameService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

//En separat controller för hantering av anrop gällande själva spelet
@CrossOrigin(origins = "*")  // Allows all origins to access
@RestController
@RequestMapping("api/games")
public class GameController {

    private final GameService gameService; //Skapar ett objekt av typen GameService

    //Konstruktör injektion av gameService dependency i GameController klassen för att kunna ha tillgång till dess metoder
    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    //Starta ett spel
    @PostMapping("/game")
    public ResponseEntity<GameEntity> startGame(@RequestBody PlayerIdDTO playerIdDTO) {
        GameEntity newGame = gameService.startGame(playerIdDTO);
        return new ResponseEntity<>(newGame, HttpStatus.OK);
    }




    //Få en lista med öppna spel
    @GetMapping("/games")
    public ResponseEntity<List<GameResponseDTO>> getOpenGames() {
        List<GameResponseDTO> openGames = gameService.getOpenGames();
        return ResponseEntity.ok(openGames);
    }




    //Delta i ett öppet spel

    //senaste 1.2
    @PostMapping("/join")
    public ResponseEntity<GameResponseDTO> joinGame(@RequestHeader("gameId") UUID gameId, @RequestBody PlayerIdDTO playerIdDTO) {
        return gameService.joinGame(gameId, playerIdDTO);
    }





    //Få aktuell information om spelet
    @PostMapping("/{gameId}")
    public GameResponseDTO getGameInfo(@PathVariable UUID gameId, @RequestBody PlayerIdDTO playerIdDTO) {
        return gameService.getGameInfo(gameId, playerIdDTO);
    }



     //Final 1.2 with PlayerIdDTO as in getGameInfo method(which works)
    @PostMapping("/move")
    public GameResponseDTO move(@Validated @RequestBody MoveRequestDTO moveRequest) {


        return gameService.move(
                moveRequest.getPlayerMove(),
                new PlayerIdDTO(moveRequest.getPlayerId()),  // Create PlayerIdDTO from player ID
                moveRequest.getGameId()
        );
    }



}





