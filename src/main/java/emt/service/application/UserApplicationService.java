package emt.service.application;

import emt.model.dto.CreateUserDto;
import emt.model.dto.LoginDto;
import emt.model.dto.LoginResponseDto;
import emt.model.dto.UpdateUserDto;

import java.util.Optional;

public interface UserApplicationService {
    Optional<UpdateUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginDto loginDto);

    Optional<UpdateUserDto> findByUsername(String username);
}
