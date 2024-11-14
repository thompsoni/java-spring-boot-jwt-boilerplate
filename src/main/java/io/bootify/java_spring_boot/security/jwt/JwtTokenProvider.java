package io.bootify.java_spring_boot.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpirationDate;

    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
        String token = Jwts.builder()
            .subject(username)
            .issuedAt(new Date())
            .expiration(expireDate)
            .signWith(key())
            .compact();

        return token;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsername(String token){
        return Jwts.parser()
            .verifyWith((SecretKey)key())
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }

    public Claims getClaims(String token){
        return Jwts.parser()
            .verifyWith((SecretKey)key())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public boolean validateToken(String token){
        Jwts.parser()
            .verifyWith((SecretKey)key())
            .build()
            .parse(token);
        return true;
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith((SecretKey)key())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
