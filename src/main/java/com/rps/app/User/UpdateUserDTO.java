package com.rps.app.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

//Request DTO - Skickar in data till servern
@ToString
@NoArgsConstructor
@Data
public class UpdateUserDTO {

    @JsonProperty
    String username;
    String password;
    // Detta request DTO krävs för uppdatering av ovannämnda uppgifter i user_entity tabellen

}
