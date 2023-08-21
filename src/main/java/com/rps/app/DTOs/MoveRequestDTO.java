package com.rps.app.DTOs;

import com.rps.app.entities.Move;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@ToString
@NoArgsConstructor
@Data
public class MoveRequestDTO { //DTO för att få in och mappa både spelarnas id-n och Move.


    private Move playerMove;
    private UUID playerId;
    private UUID gameId;
}
