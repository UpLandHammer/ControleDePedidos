package br.com.hammer.controledepedidos.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.hammer.controledepedidos.domain.Pedido;

public interface EmailService {
	
	public void sendOrderConfirmationEmail(Pedido obj);
	
	public void sendEmail(SimpleMailMessage msg);

}
