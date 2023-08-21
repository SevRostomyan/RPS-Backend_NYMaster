package com.rps.app.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//Request DTO - Skickar in data till servern
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdatePlayerDTO { //DTO för att lägga till namn på spelare i setName metoden i PlayerService

    @JsonProperty
    String name;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
