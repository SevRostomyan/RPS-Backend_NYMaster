package com.rps.app.DTOs;

import lombok.*;


import java.util.UUID;

//Har skapat DTOn för att mappa spelarnas id-n i gameInfo och joinGame metoderna och inte exponera PlayerEntity
//@ToString
@NoArgsConstructor
@Data
public class PlayerIdDTO {



   //Response DTO - Får ut data från servern
  //@JsonProperty
   UUID playerId;

 public PlayerIdDTO(UUID playerId) {
    this.playerId = playerId;
 }

}
