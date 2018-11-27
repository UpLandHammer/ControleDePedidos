package br.com.hammer.controledepedidos.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.hammer.controledepedidos.domain.Produto;
import br.com.hammer.controledepedidos.dto.ProdutoDTO;
import br.com.hammer.controledepedidos.resources.utils.URL;
import br.com.hammer.controledepedidos.services.ProdutoService;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoResurce {

	@Autowired
	private ProdutoService service;

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {
		Produto pedido = service.find(id);
		return ResponseEntity.ok().body(pedido);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(	@RequestParam(value="nome", defaultValue="nome") String nome,
														@RequestParam(value="categorias", defaultValue="categorias") String categorias,
														@RequestParam(value="page", defaultValue="0") Integer page,
														@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage,
														@RequestParam(value="orderBy", defaultValue="nome")String orderBy,
														@RequestParam(value="direction", defaultValue="ASC")String direction) {
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> categoriaList = service.search(nomeDecoded, ids, page,linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = categoriaList.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

}
