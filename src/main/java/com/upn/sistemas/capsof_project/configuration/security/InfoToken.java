package com.upn.sistemas.capsof_project.configuration.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.upn.sistemas.capsof_project.model.User;
import com.upn.sistemas.capsof_project.service.IUserService;

@Component
public class InfoToken implements TokenEnhancer {

	@Autowired
	private IUserService userService;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		User user = userService.findByUserEmail(authentication.getName());
		Map<String, Object> info = new HashMap<>();

		info.put("user_id", user.getUserId());
		info.put("user_name", user.getUserNames());
		info.put("user_last_name", user.getUserLastNames());

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

		return accessToken;
	}

}
