package com.project.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private final String jwtSecret = "YS1zdHJpbmctc2VjcmV0LWF0LWxlYXN0LTI1Ni1iaXRzLWxvbmc=";
    private final int jwtExpirationMs = 172800000;

    //Generate Token
    public String generateTokenFromUsername(UserDetails userDetails) {
        String userName =userDetails.getUsername();
        return Jwts.builder()
                .subject(userName)
                .claim("roles",userDetails.getAuthorities().stream()
                        .map(a->a.getAuthority()).toList()
                )
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith((SecretKey) key())
                .compact();
    }

    //REAL VALIDATION
    public boolean validateJwtToken(String jwtToken) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build()
                    .parseSignedClaims(jwtToken);

            System.out.println("✅ Token is VALID");
            return true;

        } catch (Exception e) {
            System.out.println("❌ Token INVALID: " + e.getMessage());
        }

        return false;
    }

    //Secret Key
    public Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    //FIXED HEADER EXTRACTION
    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken == null) {
            return null;
        }

        String headerValue = bearerToken.trim();
        if (headerValue.isEmpty()) {
            return null;
        }

        if (headerValue.startsWith("Bearer ")) {
            String token = headerValue.substring(7).trim();
            return token.isEmpty() ? null : token;
        }

        
        return headerValue;
    }

    //Extract Username
    public String getUsernameFromToken(String jwt) {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build()
                .parseSignedClaims(jwt)
                .getPayload()
                .getSubject();
    }

    public Claims getAllClaims(String jwt) {
        return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(jwt).getPayload();
    }
}