package nttdata.demo.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class JwtTokenProvider {
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length:3600000}")
    private long accessTokenLength = 3600000; // 1h

    @Value("${security.jwt.token.expire-refresh-token:604800000}")
    private long refreshTokenLength = 604800000; // 1d

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, List<String> roles) {
        try {
            Map<String, Object> claims = new HashMap<>();
            claims.put("roles", roles);
            Date now = new Date();
            Date validity = new Date(now.getTime() + accessTokenLength);
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(username)
                    .setIssuedAt(now)
                    .setExpiration(validity)
                    .signWith(SignatureAlgorithm.HS256, secretKey)
                    .compact();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public UsernamePasswordAuthenticationToken getAuthentication(String username,
                                                                 List<String> roles) {

        Collection<GrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        roles.forEach(role -> simpleGrantedAuthorities.add(new SimpleGrantedAuthority(role)));
        return new UsernamePasswordAuthenticationToken(username, null, simpleGrantedAuthorities);
    }

    public String resolveToken(HttpServletRequest req) {
        String bearToken = req.getHeader("Authorization");
        if (bearToken != null && bearToken.startsWith("Bearer ")) {
            return bearToken.substring(7);
        }
        return null;
    }

    public Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtException("authentication invalid");
        }
    }
}
