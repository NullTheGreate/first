package com.first.first.Utils;

import java.security.Key;
import java.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.SecretKey;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;


@Component
public class JwtUtils {

    private final SecretKey key = Jwts.SIG.HS512.key().build();

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claim, String subject) {
        return Jwts.builder().claims().empty().add(claim).and().subject(subject).signWith(key)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)).compact();
    }

    public boolean validateToken(String jwtToken) {
        return (!isTokenExpired(jwtToken));
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extrClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extrClaims(String token) {
        try {
            return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
            return null;
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
            return null;
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: " + e.getMessage());
            return null;
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
            return null;
        }

    }

    private boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }


}
