package br.com.hammer.controledepedidos.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.hammer.controledepedidos.domain.Cliente;
import br.com.hammer.controledepedidos.domain.Pedido;

public interface EmailService {
	
	public void sendOrderConfirmationEmail(Pedido obj);
	
	public void sendEmail(SimpleMailMessage msg);

	public void sendOrderConfirmationHtmlEmail(Pedido obj);
	
	public void sendHtmlEmail(MimeMessage msg);

	public void sendNewPasswordEmail(Cliente cliente, String newPass);
}
