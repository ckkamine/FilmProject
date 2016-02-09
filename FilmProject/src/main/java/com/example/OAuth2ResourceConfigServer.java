package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.entities.Utilisateur;

@Configuration
@EnableResourceServer
public class OAuth2ResourceConfigServer extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/rest/gerant/**").hasRole(Utilisateur.GERANT)
				.and().authorizeRequests()
				.antMatchers("/rest/admin/**").hasRole(Utilisateur.ADMINISTRATEUR)
//				.and().authorizeRequests()
//				.antMatchers("/rest/producteur/**").hasRole(Utilisateur.PRODUCTEUR)
				.and().authorizeRequests()
				.antMatchers("/rest/user").authenticated()
				.and().authorizeRequests()
				.antMatchers(HttpMethod.GET, "/").permitAll();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId("Film_Ressources");
	}
	
	

}
