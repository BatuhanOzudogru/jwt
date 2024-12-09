package com.batuhanozudogru.jwt.service;

import com.batuhanozudogru.jwt.dto.DtoUser;
import com.batuhanozudogru.jwt.jwt.AuthRequest;
import com.batuhanozudogru.jwt.jwt.AuthResponse;

public interface AuthService {

    public DtoUser register (AuthRequest authRequest);

    AuthResponse authenticate(AuthRequest authRequest);
}
