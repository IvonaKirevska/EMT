package emt.model.domain.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_LIBRARIAN;


    @Override
    public String getAuthority() {
        return name();
    }
}
