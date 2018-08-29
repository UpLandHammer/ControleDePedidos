package br.com.hammer.controledepedidos.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResouce {

	@RequestMapping(method=RequestMethod.GET)
	public String lista() {
		return "Rest Ok!";
	}
}
