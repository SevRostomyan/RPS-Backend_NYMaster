package com.rps.app.controller;
import com.rps.app.DTOs.UpdatePlayerNameDTO;
import com.rps.app.entities.PlayerEntity;
import com.rps.app.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;


//Controller för anrop kopplade till token/spelare

@CrossOrigin(origins = "*")  // Allows all origins to access
@RestController

@RequestMapping(value = "/api/user",
        produces = MediaType.APPLICATION_JSON_VALUE
        )
//@RequestMapping( "api/user" )   //Anrop till playerServices
public class PlayerController {

private final PlayerService playerService;

    @Autowired  //Kopplat controller med playerService
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }







    //Senaste 1.2
    @PostMapping("/auth/token")
    public ResponseEntity<UUID> generateToken() {
        PlayerEntity playerEntity = playerService.generateToken();
        return new ResponseEntity<>(playerEntity.getPlayerId(), HttpStatus.OK);
    }

    //Setter ett namn på UUID ovan. Namnet får den via @RequestBody som kräver från postsystemet att posta in ett jayson
    //...body med namn och koppla det till player med angivet player id.
    @PostMapping("/name")
    public ResponseEntity<Void> setName(@RequestHeader("Token") UUID playerId, @RequestBody UpdatePlayerNameDTO requestDTO) {
        playerService.setName(playerId, requestDTO);
        return new ResponseEntity<>(HttpStatus.OK); //Returnerar HTTPStatus.Ok i form av HTTP/1.1 200 som betyder att allt gick väl
    }

}


