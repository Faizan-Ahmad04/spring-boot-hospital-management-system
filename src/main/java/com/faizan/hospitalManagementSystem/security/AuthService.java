package com.faizan.hospitalManagementSystem.security;

import com.faizan.hospitalManagementSystem.dto.LoginRequestDto;
import com.faizan.hospitalManagementSystem.dto.LoginResponseDto;
import com.faizan.hospitalManagementSystem.dto.SignupResponseDto;
import com.faizan.hospitalManagementSystem.entity.User;
import com.faizan.hospitalManagementSystem.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        User user = modelMapper.map(authentication.getPrincipal(), User.class);

        String token = authUtil.generateAccessToken(user);

        return LoginResponseDto.builder()
                .jwt(token)
                .userId(user.getId())
                .build();
    }

    public SignupResponseDto signup(LoginRequestDto signupRequestDto) {
        log.info("signupRequestDto{}", signupRequestDto);
        User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);
        if (user != null) throw new IllegalArgumentException("User already exists");

        signupRequestDto.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        user = userRepository.save(modelMapper.map(signupRequestDto, User.class));

        System.out.print("user:==============" + user);
        return SignupResponseDto.builder()
                .username(user.getUsername())
                .id(user.getId())
                .build();
    }
}
