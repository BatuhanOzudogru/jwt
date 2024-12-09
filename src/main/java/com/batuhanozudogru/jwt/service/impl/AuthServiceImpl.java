package com.batuhanozudogru.jwt.service.impl;

import com.batuhanozudogru.jwt.dto.DtoUser;
import com.batuhanozudogru.jwt.jwt.AuthRequest;
import com.batuhanozudogru.jwt.jwt.AuthResponse;
import com.batuhanozudogru.jwt.jwt.JwtService;
import com.batuhanozudogru.jwt.model.RefreshToken;
import com.batuhanozudogru.jwt.model.User;
import com.batuhanozudogru.jwt.repository.RefreshTokenRepository;
import com.batuhanozudogru.jwt.repository.UserRepository;
import com.batuhanozudogru.jwt.service.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    private RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
        return refreshToken;
    }

    @Override
    public DtoUser register(AuthRequest authRequest) {
        DtoUser dtoUser = new DtoUser();
        User user = new User();

        user.setUsername(authRequest.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(authRequest.getPassword()));

        User savedUser = userRepository.save(user);
        BeanUtils.copyProperties(savedUser, dtoUser);
        return dtoUser;
    }

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        try {
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
            authenticationProvider.authenticate(auth);

            Optional<User> optionalUser = userRepository.findByUsername(authRequest.getUsername());

            String accessToken = jwtService.generateToken(optionalUser.get());
            RefreshToken refreshToken = refreshTokenRepository.save(createRefreshToken(optionalUser.get()));
            return new AuthResponse(accessToken,refreshToken.getRefreshToken());
        } catch (Exception e) {
            System.out.println("Hata oluştu" + e.getMessage());
        }
        return null;

    }
}
