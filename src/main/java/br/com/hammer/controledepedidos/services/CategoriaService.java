package br.com.hammer.controledepedidos.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hammer.controledepedidos.domain.Categoria;
import br.com.hammer.controledepedidos.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria buscar(Integer id) {
		return repository.findOne(id);
	}

	public void save(String nomeCategoria) {
		Categoria categoria = new Categoria(null, nomeCategoria);
		repository.save(Arrays.asList(categoria));
	}
}
