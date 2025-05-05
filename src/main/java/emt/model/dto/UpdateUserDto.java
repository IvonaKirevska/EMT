package emt.model.dto;

import emt.model.domain.User;
import emt.model.domain.enums.Role;

public record UpdateUserDto(String username, String name, String surname, Role role) {

    public static UpdateUserDto from(User user) {
        return new UpdateUserDto(
                user.getUsername(),
                user.getName(),
                user.getSurname(),
                user.getRole()
        );
    }

    public User toUser() {
        return new User(username, name, surname, role.name());
    }
}