package holu.oauth.global.config.security.jwt;

import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    public Key refreshSecret;
    public Key accessSecret;

    @Value("${jwt.accessSecret}")
    private String accessSecretValue;
    @Value("${jwt.refreshSecret}")
    private String refreshSecretValue;

    public void init() {
        this.accessSecret = Keys.hmacShaKeyFor(accessSecretValue.getBytes(StandardCharsets.UTF_8));
        this.refreshSecret = Keys.hmacShaKeyFor(refreshSecretValue.getBytes(StandardCharsets.UTF_8));
    }
}
