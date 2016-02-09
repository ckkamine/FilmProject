package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;


@SpringBootApplication
public class FilmProjectApplication {

	public static void main(String[] args) {
		ApplicationContext ctx= SpringApplication.run(FilmProjectApplication.class, args);
		ctx.getBean(DatbaseInit.class).init();
	}
	
	
}


class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FilmProjectApplication.class);
	}
}
