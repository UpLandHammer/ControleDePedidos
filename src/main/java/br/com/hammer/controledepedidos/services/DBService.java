package br.com.hammer.controledepedidos.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hammer.controledepedidos.domain.Categoria;
import br.com.hammer.controledepedidos.domain.Cidade;
import br.com.hammer.controledepedidos.domain.Cliente;
import br.com.hammer.controledepedidos.domain.Endereco;
import br.com.hammer.controledepedidos.domain.Estado;
import br.com.hammer.controledepedidos.domain.ItemPedido;
import br.com.hammer.controledepedidos.domain.PagamentoComBoleto;
import br.com.hammer.controledepedidos.domain.PagamentoComCartao;
import br.com.hammer.controledepedidos.domain.Pedido;
import br.com.hammer.controledepedidos.domain.Produto;
import br.com.hammer.controledepedidos.domain.enums.EstadoPagamento;
import br.com.hammer.controledepedidos.domain.enums.TipoCliente;
import br.com.hammer.controledepedidos.repositories.CategoriaRepository;
import br.com.hammer.controledepedidos.repositories.CidadeRepository;
import br.com.hammer.controledepedidos.repositories.ClienteRepository;
import br.com.hammer.controledepedidos.repositories.EnderecoRepository;
import br.com.hammer.controledepedidos.repositories.EstadoRepository;
import br.com.hammer.controledepedidos.repositories.ItemPedidoRepository;
import br.com.hammer.controledepedidos.repositories.PagamentoRepository;
import br.com.hammer.controledepedidos.repositories.PedidoRepository;
import br.com.hammer.controledepedidos.repositories.ProdutoRepository;

@Service
public class DBService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	
	public void instantiateTestDatabase() throws ParseException {
	
		Categoria cat1 = new Categoria(null, "Higiene Íntima");
		Categoria cat2 = new Categoria(null, "Bazar");
		Categoria cat3 = new Categoria(null, "Eletronicos");
		Categoria cat4 = new Categoria(null, "Cama mesa e banho");
		Categoria cat5 = new Categoria(null, "Mercearia");
		Categoria cat6 = new Categoria(null, "Charutaria");
		Categoria cat7 = new Categoria(null, "Bebidas");
		Categoria cat8 = new Categoria(null, "Textil");

		Produto p1 = new Produto(null, "Computador", 2000.0 );
		Produto p2 = new Produto(null, "Impressora", 400.0 );
		Produto p3 = new Produto(null, "Mouse", 120.0 );
		Produto p4 = new Produto(null, "Mesa de Escritorio", 300.0);
		Produto p5 = new Produto(null, "Toalha", 50.0);
		Produto p6 = new Produto(null, "Colcha", 200.0);
		Produto p7 = new Produto(null, "TV true color", 1200.0);
		Produto p8 = new Produto(null, "Roçadeira", 800.0);
		Produto p9 = new Produto(null, "Abajour", 100.0);
		Produto p10 = new Produto(null, "Pendente", 180.0);
		Produto p11 = new Produto(null, "Shampoo", 90.0);
		

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));

		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		categoriaRepository.save(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.save(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

		Estado mg = new Estado(null, "Minas Gerais");
		Estado sp = new Estado(null, "São Paulo");

		Cidade bhCity = new Cidade(null, "Belo Horizonte", mg);
		Cidade spCity = new Cidade(null, "São Paulo", sp);
		Cidade cmpCity = new Cidade(null, "Campinas", sp);

		mg.getCidades().addAll(Arrays.asList(bhCity));
		sp.getCidades().addAll(Arrays.asList(spCity, cmpCity));

		estadoRepository.save(Arrays.asList(mg, sp));
		cidadeRepository.save(Arrays.asList(bhCity, spCity, cmpCity));

		Cliente maria = new Cliente(null, "Maria", "maria@gmail.com", "56689974442", TipoCliente.PESSOAFISICA );
		maria.getTelefones().addAll(Arrays.asList("+55 11 988776655", "+55 11 987654321"));

		Endereco residencia = new Endereco(null, "Rua Santa Rita", "239", "Casa 2", "Vila Ubirajara", "11850000",maria, bhCity);
		Endereco trabalho = new Endereco(null, "Av da Saudade", "1500", "Casa 29", "Centro", "11850000",maria, bhCity);

		maria.getEnderecos().addAll(Arrays.asList(residencia, trabalho));

		clienteRepository.save(Arrays.asList(maria));
		enderecoRepository.save(Arrays.asList(residencia, trabalho));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), maria, residencia);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 00:00"), maria, trabalho);

		PagamentoComCartao pagamentoComCartao = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagamentoComCartao);

		PagamentoComBoleto pagamentoComBoleto = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagamentoComBoleto);

		maria.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.save(Arrays.asList(ped1, ped2));
		pagamentoRepository.save(Arrays.asList(pagamentoComCartao, pagamentoComBoleto));

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00 );
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		ped1.getItens().addAll(Arrays.asList(ip1));
		ped2.getItens().addAll(Arrays.asList(ip3));
		ped1.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));
	}
}
