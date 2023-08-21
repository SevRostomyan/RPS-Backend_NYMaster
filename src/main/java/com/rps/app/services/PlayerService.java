package com.rps.app.services;

import com.rps.app.DTOs.UpdatePlayerNameDTO;
import com.rps.app.entities.PlayerEntity;
import com.rps.app.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


@Service
public class PlayerService {

    private static PlayerRepository playerRepository;

    //Constructor
    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        PlayerService.playerRepository = playerRepository;
    }




    //Senaste 1.2
    public PlayerEntity generateToken() {
        UUID uuid = UUID.randomUUID();
        System.out.println("Generated UUID: " + uuid);

        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setPlayerId(uuid);

        try {
            playerEntity = playerRepository.save(playerEntity);
            System.out.println("UUID saved in the database: " + playerEntity.getPlayerId());
        } catch (Exception e) {
            System.out.println("Error saving the UUID in the database: " + e.getMessage());
        }

        return playerEntity;
    }



    public void setName(UUID playerId, UpdatePlayerNameDTO requestDTO) {
        Optional<PlayerEntity> player = playerRepository.findById(playerId);

        if (player.isPresent()) {
            PlayerEntity playerEntity = player.get();
            playerEntity.setPlayerName(requestDTO.getName());
            playerRepository.save(playerEntity);

            // fetch the player entity right after the save operation to check whether it's saved correctly
            Optional<PlayerEntity> savedPlayer = playerRepository.findById(playerId);
            if (savedPlayer.isPresent()) {
                System.out.println("Player entity saved successfully with UUID: " + savedPlayer.get().getPlayerId());
            } else {
                System.out.println("Failed to save player entity with UUID: " + playerId);
            }

        } else {
            throw new RuntimeException("Token not found: " + playerId);
        }
    }


}