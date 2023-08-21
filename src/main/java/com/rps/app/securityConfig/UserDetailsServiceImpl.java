package com.rps.app.securityConfig;

/*import com.rps.app.User.UserEntity;
import com.rps.app.User.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Denna klass är vår egen implementation av UserDetailsServcie interfacet som ansvarar för att ta fram autentiserings och identifierings information om användare.
//Interface har bara en metod som heter LoadUserByUsername() som kan omskrivas (Overridden) för att anpassa processen av att hitta en kund
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository; //Vi kopplar repository med inloggningsservicen som kan hämta användardata därifrån
    }

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("username: {}", username); // Det här visar log för värdet av username parametern


        UserEntity userEntity = userRepository
                .findUserByUsername(username)
                .orElseThrow();

        return User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .authorities(new SimpleGrantedAuthority("ROLE_ADMIN"))
                .build();
    }
}*/
