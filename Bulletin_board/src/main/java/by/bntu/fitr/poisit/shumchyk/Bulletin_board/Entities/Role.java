package by.bntu.fitr.poisit.shumchyk.Bulletin_board.Entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {

    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}