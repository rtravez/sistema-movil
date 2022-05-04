package ec.uisrael.sisreha.com.auth;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * <b> Descripcion de la clase, interface o enumeracion.</b>
 * 
 * @author renetravez
 * @version $1.0$
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	@Autowired
	private InfoAdionalToken infoAdionalToken;

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient("frontend-sisreha").secret(passwordEncoder.encode("frontend-sisreha")).scopes("read", "write").authorizedGrantTypes("password", "refresh_token")
				.accessTokenValiditySeconds(3600).refreshTokenValiditySeconds(3600);

	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAdionalToken, accessTokenConverter()));
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore()).accessTokenConverter(accessTokenConverter()).tokenEnhancer(tokenEnhancerChain);
	}

	/**
	 * <b> Incluir aqui la descripcion del metodo. </b>
	 * <p>
	 * [Author renetravez, 21 sep. 2020]
	 * </p>
	 *
	 * @return TokenStore
	 */

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	/**
	 * <b> Metodo que permite almacenar los datos de autentificacion. </b>
	 * <p>
	 * [Author renetravez, 21 sep. 2020]
	 * </p>
	 *
	 * @return AccessTokenConverter
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();		
		jwtAccessTokenConverter.setSigningKey("-----BEGIN RSA PRIVATE KEY-----\n" + "MIIEpQIBAAKCAQEAzUo6mnUZKVhlpHk13RZB4PL7jMmxi6LzDApueLi3RNuqMhNZ\n"
				+ "rh7+C8Tx7Od4g29mSRZvotpVKepVOHO7zkwtmvhwdOw9XfD9QE2XA7eWnOdNwS3L\n" + "olmPKm7vGJzlubcyhhWrb1CWlKGf9pfzU8JwYIpVzWU0TixLT+NjxJZVOYozrZ4w\n"
				+ "lQHv4OKmk/P5kUX4QDPgLqcQw9hvc8hGsOqNNMsw5EnCoJulApTVH8Vaz3sEqfaN\n" + "ETjMI21B5gffT7M/o5urpvYZ7esNiH/L8NUqs5c4h1ReZlXRrXTmxKgjOrrqqIIc\n"
				+ "RmY6WQ1cV/hjQHOXOikOnNPNZPlZqMT97+A+WQIDAQABAoIBAQCsEg/U71nLwgBQ\n" + "QBjZ25IUjxYyB8z9kfPOn9/A6HMVEFoz5uxAelHOY6G0RMBKytEdaeTgx8iifXw4\n"
				+ "+Sjt3gRL5Pm9x1YYq6paf66YuwVPNsGBrnUE+7R4iMt4Aeq8I8YBhaBQiWft5RtD\n" + "glG+Xzf6c3k/1BhnfdESgTJa0PdWZfPNW05fEB1XSN7q26HNBECx9Q0DDpSlaHCy\n"
				+ "ZMwEZBzBzjpD6TKtGtfPHAWIoragKs23UJo+6potXKfmL6IX/To5mAqHxkWN0ZiH\n" + "F2bk94fP5vM68QPtCEEst0+2v48xoOhC+0lm799bRLFc2tde2WiNmzLwO9IbnkrY\n"
				+ "WrJuIdoBAoGBAPJMuw+MKj6ERboCbInIGK8cmzsQ5cgsmylWAoEBAagZdjr81Ofd\n" + "aUucLwV6DF86YXDmfmn4j/mIT+KFG2me3vFj5/xW0ibdS8fDHoOaVwKT4qkrMew5\n"
				+ "PpVb9BO6++YV+X0eICw16JUgEvRDw+uDD19QRN+OIRzlu7kTbdH2bAkRAoGBANjl\n" + "yOIqT4RKQnw4E5jUsAb9Wt9s7RxH118B/XrH+ZeWbLWLbV4KvBD9BQXaXUCw0BDp\n"
				+ "MDZDAiV3YotXC8aTqLGJmpMSi8snVMVxDCOJ0ccUi4QcRMBKv4SynyuXuOyp8lox\n" + "BMRgre8jmK+w+guJyfcL9/pvlQ6/7IbFhVoNOyDJAoGBAO+dmCQw/hnjHz1rZkD/\n"
				+ "pAGQrabE+Ch0jGFdkI5ZG3VM8NI29Kn0GUxPwWSOJSwLhQ9ssjlEOcWQ20dtUQWr\n" + "ulhH78Rpa9xJXsbHnCaTfFatPysC75Tnq49zZzYX5ULOGnN0VmbCTSckl/n+BgOj\n"
				+ "EZG1xNa8LNzyCCpoH2PTaVWxAoGAGfGKULsc3ccAItmWRn6SO5PMbdManXpeYYfc\n" + "aPZIfYBJqoFt6ITCMAoDssqGwvkjOhKaaLMlK5QXHnGf//WjhPDfo13hiMBAnIlE\n"
				+ "GXAfvn9PnNLAyntmYxn2fgFc5joBuhYI6vBhfaw1VDaIAh9OIBW3aKY2HxiH0ysH\n" + "I1lXzDkCgYEAlPtHiibIN+Z6AaLC96yh2mZymzg3c69wsvt7VGKp5nMwqu5Rym6q\n"
				+ "NmGDMaLk7PR92oi6mIugelcGLsxsKewvpnbpiGgTw4rmncwo0V2sfQJpKcrPfEZ6\n" + "t5TLAmU1N9uPP/qhoa9JNR6btq0ljmFkjY/1DMBjBlLfOAQSaVnkHTE=\n" + "-----END RSA PRIVATE KEY-----");
		jwtAccessTokenConverter.setVerifierKey("-----BEGIN PUBLIC KEY-----\n" + "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzUo6mnUZKVhlpHk13RZB\n"
				+ "4PL7jMmxi6LzDApueLi3RNuqMhNZrh7+C8Tx7Od4g29mSRZvotpVKepVOHO7zkwt\n" + "mvhwdOw9XfD9QE2XA7eWnOdNwS3LolmPKm7vGJzlubcyhhWrb1CWlKGf9pfzU8Jw\n"
				+ "YIpVzWU0TixLT+NjxJZVOYozrZ4wlQHv4OKmk/P5kUX4QDPgLqcQw9hvc8hGsOqN\n" + "NMsw5EnCoJulApTVH8Vaz3sEqfaNETjMI21B5gffT7M/o5urpvYZ7esNiH/L8NUq\n"
				+ "s5c4h1ReZlXRrXTmxKgjOrrqqIIcRmY6WQ1cV/hjQHOXOikOnNPNZPlZqMT97+A+\n" + "WQIDAQAB\n" + "-----END PUBLIC KEY-----");
		return jwtAccessTokenConverter;
	}

}
