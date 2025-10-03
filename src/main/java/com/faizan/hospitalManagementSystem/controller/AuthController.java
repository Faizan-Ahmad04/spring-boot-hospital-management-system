package com.faizan.hospitalManagementSystem.controller;

import com.faizan.hospitalManagementSystem.dto.LoginRequestDto;
import com.faizan.hospitalManagementSystem.dto.LoginResponseDto;
import com.faizan.hospitalManagementSystem.dto.SignUpRequestDto;
import com.faizan.hospitalManagementSystem.dto.SignupResponseDto;
import com.faizan.hospitalManagementSystem.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignUpRequestDto signupRequestDto) {
        return new ResponseEntity<>(authService.signup(signupRequestDto), HttpStatus.CREATED);
    }
}
