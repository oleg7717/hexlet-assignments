package exercise.component;

// BEGIN

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@ConfigurationProperties(prefix = "rsa")
@Getter
@Setter
public class RsaKeyProperties {
	private RSAPrivateKey privateKey;
	private RSAPublicKey publicKey;
}
// END
