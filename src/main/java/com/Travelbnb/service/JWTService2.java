package com.Travelbnb.service;

import com.Travelbnb.payload.LoginDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService2 {

    @Value("${jwt.algorithm.key}")
    private String key;

    @Value("${jwt.issuer}")
    private String issuer;

    private Algorithm algo;

    @Value("${jwt.expiry.duration}")
    private int expiry;

    @PostConstruct
    public void construct(){
        algo= Algorithm.HMAC256(key);
    }

    public String generateToken(LoginDto dto){
        return JWT.create()
                .withClaim("username",dto.getUsername())
                .withIssuer(issuer)
                .withExpiresAt(new Date(System.currentTimeMillis()))
                .sign(algo);
    }
}
