package br.com.hammer.controledepedidos;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.hammer.controledepedidos.domain.Categoria;
import br.com.hammer.controledepedidos.repositories.CategoriaRepository;

@SpringBootApplication
public class IsApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(IsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria higiene = new Categoria(null, "Higiene Íntima");
		Categoria bazar = new Categoria(null, "Bazar");
		
		repository.save(Arrays.asList(higiene, bazar));
	}
}
