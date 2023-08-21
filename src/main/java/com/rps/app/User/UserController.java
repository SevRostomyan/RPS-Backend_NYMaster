package com.rps.app.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Controller för anrop kopplade till användare

@RestController
@RequestMapping(value = "/api/login",
        produces = MediaType.APPLICATION_JSON_VALUE
)
//@RequestMapping( "api/user" )   //Anrop till playerServices
public class UserController {

    private final UserService userService;

    @Autowired  //Kopplat controller med userService
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Setter ett lösenord och password på användaren. Uppgifterna får den via @RequestBody UpdateUserDTO som kräver från
    // postsystemet att posta in ett jayson body med username och password med angivet userId från tabellen user_entity.

    /*@PostMapping("/{userId}/name")
    public ResponseEntity<Void> setUserNameAndPassword(@PathVariable Integer userId, @RequestBody UpdateUserDTO requestDTO) {
        userService.UpdateUserNameAndPassword(userId, requestDTO);
        return new ResponseEntity<>(HttpStatus.OK);//Returnerar HTTPStatus.Ok i form av HTTP/1.1 200 som betyder att allt gick väl
    }*/

    @PostMapping("/name")
    public ResponseEntity<Void> setUserNameAndPassword(@RequestHeader("UserId") Integer userId, @RequestBody UpdateUserDTO requestDTO) {
        userService.UpdateUserNameAndPassword(userId, requestDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}


