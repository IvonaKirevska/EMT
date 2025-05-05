package emt.model.dto;

import emt.model.domain.User;
import emt.model.domain.enums.Role;

public record CreateUserDto(
        String username,
        String password,
        String repeatPassword,
        String name,
        String surname,
        Role role
) {


    public User toUser() {
        return new User(username, password, name, surname, role);
    }
}