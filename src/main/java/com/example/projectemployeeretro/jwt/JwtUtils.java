package com.example.projectemployeeretro.jwt;

import com.example.projectemployeeretro.entity.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils implements Serializable {
    private final String jwtSecret = "vutham142857";
//    @Value("${project-employee-retro.app.jwtExpirationMs}")
    private final long jwtExp = 1000 * 60 * 5;
//    @Value("${project-employee-retro.app.jwtRefreshExpirationMs}")
    private final long jwtRefreshExpirationMs = 10 * 1000 * 60;
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }
    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claimsResolver.apply(claims);
    }
    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }
    public String generateToken(CustomUserDetails userDetails){
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExp))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }
    public Boolean validateToken(String token, CustomUserDetails userDetails, HttpServletRequest request){
        try {
            final String username = getUsernameFromToken(token);
            return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        }catch (ExpiredJwtException e){
            request.setAttribute("expired", e.getMessage());
            throw new ExpiredJwtException(e.getHeader(), e.getClaims(), "JWT token has expired");
        }
    }
    public String generateRefreshToken(CustomUserDetails customUserDetails){
        return Jwts.builder()
                .setSubject(customUserDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtRefreshExpirationMs))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }
}
