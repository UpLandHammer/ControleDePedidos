package br.com.hammer.controledepedidos.resources;

import java.util.List;
import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hammer.controlepedidos.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResouce {

	@RequestMapping(method=RequestMethod.GET)
	public List<Categoria> lista() {
		Categoria informatica = new Categoria(1, "Informatica");
		Categoria escritorio = new Categoria(2, "Escritorio");

		List<Categoria> lista = new ArrayList<Categoria>();
		lista.add(escritorio);
		lista.add(informatica);
		
		return lista;
	}
}
