package emt.service.application.impl;

import emt.model.domain.User;
import emt.model.dto.CreateUserDto;
import emt.model.dto.LoginDto;
import emt.model.dto.LoginResponseDto;
import emt.model.dto.UpdateUserDto;
import emt.security.JwtHelper;
import emt.service.application.UserApplicationService;
import emt.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserApplicationServiceImpl implements UserApplicationService {

    private final UserService userService;
    private final JwtHelper jwtHelper;

    public UserApplicationServiceImpl(UserService userService, JwtHelper jwtHelper) {
        this.userService = userService;
        this.jwtHelper = jwtHelper;
    }

    @Override
    public Optional<UpdateUserDto> register(CreateUserDto createUserDto) {
        User user = userService.register(
                createUserDto.username(),
                createUserDto.password(),
                createUserDto.repeatPassword(),
                createUserDto.name(),
                createUserDto.surname(),
                createUserDto.role()
        );
        return Optional.of(UpdateUserDto.from(user));
    }

    @Override
    public Optional<LoginResponseDto> login(LoginDto loginUserDto) {
        User user = userService.login(loginUserDto.username(),loginUserDto.password());

        String token = jwtHelper.generateToken(user);
        return Optional.of(new LoginResponseDto(token));
    }

    @Override
    public Optional<UpdateUserDto> findByUsername(String username) {
        return Optional.of(UpdateUserDto.from(userService.findByUsername(username)));
    }
}
