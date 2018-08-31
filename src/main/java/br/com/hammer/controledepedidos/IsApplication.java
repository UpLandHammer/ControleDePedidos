package br.com.hammer.controledepedidos;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.hammer.controledepedidos.domain.Categoria;
import br.com.hammer.controledepedidos.domain.Cidade;
import br.com.hammer.controledepedidos.domain.Estado;
import br.com.hammer.controledepedidos.domain.Produto;
import br.com.hammer.controledepedidos.repositories.CategoriaRepository;
import br.com.hammer.controledepedidos.repositories.CidadeRepository;
import br.com.hammer.controledepedidos.repositories.EstadoRepository;
import br.com.hammer.controledepedidos.repositories.ProdutoRepository;

@SpringBootApplication
public class IsApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	

	public static void main(String[] args) {
		SpringApplication.run(IsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria higiene = new Categoria(null, "Higiene Íntima");
		Categoria bazar = new Categoria(null, "Bazar");

		Produto pc = new Produto(null, "Computador", 2000.0 );
		Produto printer = new Produto(null, "Impressora", 400.0 );
		Produto mouse = new Produto(null, "Mouse", 120.0 );

		higiene.getProdutos().addAll(Arrays.asList(pc, printer, mouse));
		bazar.getProdutos().addAll(Arrays.asList(printer));

		pc.getCategorias().addAll(Arrays.asList(higiene));
		printer.getCategorias().addAll(Arrays.asList(bazar, higiene));
		mouse.getCategorias().addAll(Arrays.asList(higiene));

		categoriaRepository.save(Arrays.asList(higiene, bazar));
		produtoRepository.save(Arrays.asList(pc, printer, mouse));

		Estado mg = new Estado(null, "Minas Gerais");
		Estado sp = new Estado(null, "São Paulo");

		Cidade bhCity = new Cidade(null, "Belo Horizonte", mg);
		Cidade spCity = new Cidade(null, "São Paulo", sp);
		Cidade cmpCity = new Cidade(null, "Campinas", sp);

		mg.getCidades().addAll(Arrays.asList(bhCity));
		sp.getCidades().addAll(Arrays.asList(spCity, cmpCity));
		
		estadoRepository.save(Arrays.asList(mg, sp));
		cidadeRepository.save(Arrays.asList(bhCity, spCity, cmpCity));
	}
}
