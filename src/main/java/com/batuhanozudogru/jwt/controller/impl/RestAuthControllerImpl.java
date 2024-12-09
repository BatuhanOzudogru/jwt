package com.batuhanozudogru.jwt.controller.impl;

import com.batuhanozudogru.jwt.controller.RestAuthController;
import com.batuhanozudogru.jwt.dto.DtoUser;
import com.batuhanozudogru.jwt.jwt.AuthRequest;
import com.batuhanozudogru.jwt.jwt.AuthResponse;
import com.batuhanozudogru.jwt.jwt.RefreshTokenRequest;
import com.batuhanozudogru.jwt.service.AuthService;
import com.batuhanozudogru.jwt.service.RefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthControllerImpl implements RestAuthController {


    @Autowired
    private AuthService authService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    @Override
    public DtoUser register(@Valid @RequestBody AuthRequest authRequest) {
        return authService.register(authRequest);
    }

    @PostMapping("/authenticate")
    @Override
    public AuthResponse authenticate(@Valid @RequestBody AuthRequest authRequest) {
        return authService.authenticate(authRequest);
    }

    @PostMapping("/refreshToken")
    @Override
    public AuthResponse refreshToken(@RequestBody RefreshTokenRequest refreshToken) {
        return refreshTokenService.refreshToken(refreshToken);
    }
}
