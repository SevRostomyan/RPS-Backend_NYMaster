
package com.rps.app.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static UserRepository userRepository;

    //Constructor
    @Autowired
    public UserService(UserRepository userRepository) {
        UserService.userRepository = userRepository;
    }

   //Service för uppdatering av UserEntity via UpdateUserDTOs instance requestDTO där det anges username och password

    public void UpdateUserNameAndPassword(Integer userId, UpdateUserDTO requestDTO) {
        Optional<UserEntity> user = userRepository.findById(userId); //Skapa et playerEntity objekt och sök spelare via ovan uuid och ange värdet till objektet
        if (user.isPresent()) {  //Om spelaren motsvarande uuid-n finns
           UserEntity userEntity = user.get();  //...hämta den
            userEntity.setUsername(requestDTO.getUsername()); //Lägger till username och password
            userEntity.setPassword(requestDTO.getPassword());
            userRepository.save(userEntity); //Spara det uppdaterade entiteten via repository i databasen
        }
    }

}



