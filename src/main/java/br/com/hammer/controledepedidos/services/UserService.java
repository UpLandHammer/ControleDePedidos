package br.com.hammer.controledepedidos.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.hammer.controledepedidos.security.UserSS;

public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch(Exception e) {
			return null;
		}
	}
}
