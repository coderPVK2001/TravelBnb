package com.Travelbnb.service;

import com.Travelbnb.entity.AppUser;
import com.Travelbnb.payload.LoginDto;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    //jwt token consists of header,payload(username, issuer, expiry), signature
    // header consists of algorithm

    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.expiry.duration}")
    private int expiryTime;

    @Value("${jwt.issuer}")
    private String issuer;

    private final String USERNAME="username";

    private Algorithm algo;

    @PostConstruct                           // will automatically gets called when we run/start the application
    public void postConstruct(){

        algo= Algorithm.HMAC256(algorithmKey);   //algo is applied with secret key present inside it ;
                                                 // will be used to encrypt and decrypt the result with the help of secret key
                                                 // secret key is necessary to apply the algorithm logic without that key algo. willl not work
    }

    public String generateToken(LoginDto ldto){    //comp engg. is unemployed   //payload is being done here

        return JWT.create()
                .withClaim( USERNAME, ldto.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
                .withIssuer(issuer)
                .sign(algo);
    }


    public String getUsername(String token){

        DecodedJWT decoded = JWT.require(algo).withIssuer(issuer).build().verify(token);
        String username = decoded.getClaim(USERNAME).asString();

        return username;
    }
}
