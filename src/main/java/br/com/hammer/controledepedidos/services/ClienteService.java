package br.com.hammer.controledepedidos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hammer.controledepedidos.domain.Categoria;
import br.com.hammer.controledepedidos.domain.Cliente;
import br.com.hammer.controledepedidos.repositories.ClienteRepository;
import br.com.hammer.controledepedidos.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente buscar(Integer id) {
		Cliente dados = repository.findOne(id);
			if(dados == null) {
				throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id 
												+", Tipo: " + Cliente.class.getName());
			}
		return dados;
	}
}
