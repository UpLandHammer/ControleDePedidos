package br.com.hammer.controledepedidos.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.hammer.controledepedidos.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dBService;
	

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		dBService.instantiateTestDatabase();
		return true;
	}
}
