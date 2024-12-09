package com.batuhanozudogru.jwt.service.impl;

import com.batuhanozudogru.jwt.jwt.AuthResponse;
import com.batuhanozudogru.jwt.jwt.JwtService;
import com.batuhanozudogru.jwt.jwt.RefreshTokenRequest;
import com.batuhanozudogru.jwt.model.RefreshToken;
import com.batuhanozudogru.jwt.model.User;
import com.batuhanozudogru.jwt.repository.RefreshTokenRepository;
import com.batuhanozudogru.jwt.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private JwtService jwtService;

    public boolean isRefreshTokenExpired(Date expiredDate){
        return new Date().after(expiredDate);
    }

    public RefreshToken createRefreshToken (User user){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
        return refreshToken;
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        Optional<RefreshToken> optional = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
        if(optional.isEmpty()){
            System.out.println("Refresh token not found");
        }
        RefreshToken refreshToken = optional.get();

        if (isRefreshTokenExpired(refreshToken.getExpireDate())) {
            System.out.println("Refresh token expired");
        }

        String accessToken = jwtService.generateToken(refreshToken.getUser());
        RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(refreshToken.getUser()));


        return new AuthResponse(accessToken,savedRefreshToken.getRefreshToken());
    }
}
