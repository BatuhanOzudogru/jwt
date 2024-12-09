package com.batuhanozudogru.jwt.service;

import com.batuhanozudogru.jwt.jwt.AuthResponse;
import com.batuhanozudogru.jwt.jwt.RefreshTokenRequest;

public interface RefreshTokenService {

    AuthResponse refreshToken(RefreshTokenRequest request);
}
