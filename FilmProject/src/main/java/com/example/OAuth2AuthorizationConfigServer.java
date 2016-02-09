package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

import com.example.security.CustomUserDetailsService;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfigServer extends AuthorizationServerConfigurerAdapter{

	private TokenStore tokenStore = new InMemoryTokenStore();
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	
	
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception{
		endpoints
		.tokenStore(this.tokenStore)
		.authenticationManager(this.authenticationManager);
	}
	
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception{
		clients
			.inMemory()
				.withClient("WebClient")
				.secret("secret")
				.authorizedGrantTypes("password")
				.scopes("read", "write")
				.resourceIds("Film_Ressources")
				.accessTokenValiditySeconds(60*60);
	}
	
	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
		DefaultTokenServices tokenServices = new DefaultTokenServices();
		tokenServices.setSupportRefreshToken(true);
		tokenServices.setTokenStore(this.tokenStore);
		return tokenServices;
	}
}
