package ec.com.nashira.callcenter.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import ec.com.nashira.callcenter.entities.User;
import ec.com.nashira.callcenter.services.UserService;

@Component
public class AdditionalUserInfo implements TokenEnhancer {

	@Autowired
	private UserService userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> userInfo = new HashMap<>();
		User user = userService.findByUsernameAndDeleted(authentication.getName(), false);
		if (user != null) {
			userInfo.put("user_id", user.getId());
		}
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(userInfo);
		return accessToken;
	}

}
