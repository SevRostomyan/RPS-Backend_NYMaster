package com.rps.app.DTOs;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@ToString
@NoArgsConstructor
@Data
public class GameResponseDTO {
    private UUID gameId;
    private String playerOne;
    private String playerTwo;
    private String playerOneMove;
    private String playerTwoMove;
    private String status;
    private Integer playerOneWins;
    private Integer playerTwoWins;
    private LocalDateTime lastUpdated;
    private String message;
}
