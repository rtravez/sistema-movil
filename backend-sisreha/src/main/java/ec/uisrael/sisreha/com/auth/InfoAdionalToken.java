package ec.uisrael.sisreha.com.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import ec.uisrael.sisreha.com.entity.User;
import ec.uisrael.sisreha.com.service.IUserService;

/**
 * <b> Descripcion de la clase, interface o enumeracion. </b>
 * 
 * @author renetravez
 * @version $1.0$
 */
@Component
public class InfoAdionalToken implements TokenEnhancer {

	@Autowired
	private IUserService userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		User user = userService.findByUsername(authentication.getName()).orElse(null);
		if (user != null) {
			Map<String, Object> additionalInformation = new HashMap<>();
			additionalInformation.put("enabled", user.getEnabled());
			additionalInformation.put("username", user.getUsername());
			((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
		}
		return accessToken;
	}
}
