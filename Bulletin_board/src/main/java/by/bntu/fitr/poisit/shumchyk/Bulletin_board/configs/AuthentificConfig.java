package by.bntu.fitr.poisit.shumchyk.Bulletin_board.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class AuthentificConfig {
    @Bean(name = "auth")
    public Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}