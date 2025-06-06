package igor.henrique.security.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import igor.henrique.security.dto.UserDetailsDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;

@Service
public class JwtTokenService {

    @Value("${default-project.jwt.secret}")
    private String secret;

    @Value("${default-project.jwt.expiration}")
    private Long expiration;

    @Value("${default-project.jwt.issuer}")
    private String issuer;

    private final ZoneOffset zoneOffset = ZoneOffset.of("-03:00");

    public String generateToken(UserDetailsDTO user) {
        try{
            var algorithm = Algorithm.HMAC256(this.secret);

            return JWT.create()
                    .withIssuer(this.issuer)
                    .withSubject(user.getUsername())
                    .withIssuedAt(LocalDateTime.now().toInstant(zoneOffset))
                    .withExpiresAt(LocalDateTime.now().plusMinutes(this.expiration).toInstant(zoneOffset))
                    .withPayload(Map.of("user", user.toMap()))
                    .sign(algorithm);
        }catch (JWTCreationException jwtCreationException){
            throw new RuntimeException(jwtCreationException);
        }
    }

    public String getUserId(String token){
            var algorithm = Algorithm.HMAC256(this.secret);

            String id = JWT.require(algorithm)
                    .withIssuer(this.issuer)
                    .build()
                    .verify(token)
                    .getSubject();

            return id;

    }
}
