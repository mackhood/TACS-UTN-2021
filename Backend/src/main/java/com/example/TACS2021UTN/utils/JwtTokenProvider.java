package com.example.TACS2021UTN.utils;

import com.example.TACS2021UTN.models.user.Role;
import com.example.TACS2021UTN.models.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Component
@NoArgsConstructor
public class JwtTokenProvider {

    private final String HEADER = "Authorization";
    private final String PREFIX = "Bearer ";
    private final String SECRET = "mySecretKey";

    public String doGenerateToken(User user) {

        Claims claims = Jwts.claims().setSubject(user.getUsername());
        List<String> authorities = new ArrayList<>();
        for(Role role : user.getRoles())
            authorities.add("ROLE_" + role.getName());
        claims.put("authorities", authorities);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS256, SECRET.getBytes())
                .compact();
    }

    public Claims getClaims(String jwtToken) {
        return Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(jwtToken).getBody();
    }

}
