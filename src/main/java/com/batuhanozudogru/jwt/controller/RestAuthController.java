package com.batuhanozudogru.jwt.controller;

import com.batuhanozudogru.jwt.dto.DtoUser;
import com.batuhanozudogru.jwt.jwt.AuthRequest;
import com.batuhanozudogru.jwt.jwt.AuthResponse;
import com.batuhanozudogru.jwt.jwt.RefreshTokenRequest;

public interface RestAuthController {

    public DtoUser register(AuthRequest authRequest);

    public AuthResponse authenticate(AuthRequest authRequest);

    public AuthResponse refreshToken(RefreshTokenRequest refreshToken);
}
