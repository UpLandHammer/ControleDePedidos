package br.com.hammer.controledepedidos.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hammer.controledepedidos.domain.ItemPedido;
import br.com.hammer.controledepedidos.domain.PagamentoComBoleto;
import br.com.hammer.controledepedidos.domain.Pedido;
import br.com.hammer.controledepedidos.domain.enums.EstadoPagamento;
import br.com.hammer.controledepedidos.repositories.ClienteRepository;
import br.com.hammer.controledepedidos.repositories.ItemPedidoRepository;
import br.com.hammer.controledepedidos.repositories.PagamentoRepository;
import br.com.hammer.controledepedidos.repositories.PedidoRepository;
import br.com.hammer.controledepedidos.repositories.ProdutoRepository;
import br.com.hammer.controledepedidos.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Pedido find(Integer id) {
		Pedido dados = repository.findOne(id);
			if(dados == null) {
				throw new ObjectNotFoundException("Objeto não encontrado! Id:" + id 
												+", Tipo: " + Pedido.class.getName());
			}
		return dados;
	}

	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteRepository.findOne(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoRepository.findOne(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.save(obj.getItens());
		System.out.println(obj);
		return obj;
	}
}
