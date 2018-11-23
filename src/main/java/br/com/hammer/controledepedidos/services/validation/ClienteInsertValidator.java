package br.com.hammer.controledepedidos.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.hammer.controledepedidos.domain.Cliente;
import br.com.hammer.controledepedidos.domain.enums.TipoCliente;
import br.com.hammer.controledepedidos.dto.ClienteNewDTO;
import br.com.hammer.controledepedidos.repositories.ClienteRepository;
import br.com.hammer.controledepedidos.resources.exception.FieldMessage;
import br.com.hammer.controledepedidos.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO>{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClienteInsert dto) {
	}

	@Override
	public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(dto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(dto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));
		}
		
		if(dto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCPNJ(dto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));
		}
		
		Cliente aux = clienteRepository.findByEmail(dto.getEmail());
		
		if( aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		for(FieldMessage m : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(m.getMessage()).addPropertyNode(m.getFiedName())
			.addConstraintViolation();
		}
		return list.isEmpty();
	}
	
	

}
