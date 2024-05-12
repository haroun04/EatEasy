package com.EatEasy.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {

    @Value("${jwt.secret-key}")
    public String jwtSecretKey;
    @Value("${jwt.expiration}")
    public Long jwtExpiration;

    public String createToken(String name){
        String token = "";
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecretKey);
            token = JWT.create()
                    .withIssuer("eateasy")
                    .withSubject(name)
                    .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpiration))
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
        }
        return token;
    }

    //@Value("${jwt.secret-key}")

    public String getUsernameFromToken(String token) {
        try {
            return JWT.decode(token).getSubject();
        } catch (JWTDecodeException exception){
            return null;
        }
    }

}