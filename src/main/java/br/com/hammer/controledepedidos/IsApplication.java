package br.com.hammer.controledepedidos;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

	public static void main(String[] args) {
		SpringApplication.run(IsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria higiene = new Categoria(null, "Higiene Íntima");
		Categoria bazar = new Categoria(null, "Bazar");
		Categoria eletronicos = new Categoria(null, "Eletronicos");
		Categoria cama = new Categoria(null, "Cama mesa e banho");
		Categoria mercearia = new Categoria(null, "Mercearia");
		Categoria charutaria = new Categoria(null, "Charutaria");
		Categoria bebidas = new Categoria(null, "Bebidas");
		Categoria textil = new Categoria(null, "Textil");

		Produto pc = new Produto(null, "Computador", 2000.0 );
		Produto printer = new Produto(null, "Impressora", 400.0 );
		Produto mouse = new Produto(null, "Mouse", 120.0 );
		

		higiene.getProdutos().addAll(Arrays.asList(pc, printer, mouse));
		bazar.getProdutos().addAll(Arrays.asList(printer));

		pc.getCategorias().addAll(Arrays.asList(higiene));
		printer.getCategorias().addAll(Arrays.asList(bazar, higiene));
		mouse.getCategorias().addAll(Arrays.asList(higiene));

		categoriaRepository.save(Arrays.asList(higiene, bazar, eletronicos, cama, mercearia, charutaria, bebidas, textil));
		 
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

		Cliente maria = new Cliente(null, "Maria", "maria@gmail.com", "56689974442", TipoCliente.PESSOAFISICA );
		maria.getTelefones().addAll(Arrays.asList("+55 11 988776655", "+55 11 987654321"));

		Endereco residencia = new Endereco(null, "Rua Santa Rita", "239", "Casa 2", "Vila Ubirajara", "11850000",maria, bhCity);
		Endereco trabalho = new Endereco(null, "Av da Saudade", "1500", "Casa 29", "Centro", "11850000",maria, bhCity);

		maria.getEnderecos().addAll(Arrays.asList(residencia, trabalho));

		clienteRepository.save(Arrays.asList(maria));
		enderecoRepository.save(Arrays.asList(residencia, trabalho));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido p1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), maria, residencia);
		Pedido p2 = new Pedido(null, sdf.parse("10/10/2017 00:00"), maria, trabalho);

		PagamentoComCartao pagamentoComCartao = new PagamentoComCartao(null, EstadoPagamento.QUITADO, p1, 6);
		p1.setPagamento(pagamentoComCartao);

		PagamentoComBoleto pagamentoComBoleto = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, p2, sdf.parse("20/10/2017 00:00"), null);
		p2.setPagamento(pagamentoComBoleto);

		maria.getPedidos().addAll(Arrays.asList(p1, p2));

		pedidoRepository.save(Arrays.asList(p1, p2));
		pagamentoRepository.save(Arrays.asList(pagamentoComCartao, pagamentoComBoleto));

		ItemPedido ip1 = new ItemPedido(p1, pc, 0.00, 1, 2000.00 );
		ItemPedido ip2 = new ItemPedido(p1, mouse, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(p2, printer, 100.00, 1, 800.00);

		p1.getItens().addAll(Arrays.asList(ip1, ip2));
		p2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p1.getItens().addAll(Arrays.asList(ip2));

		itemPedidoRepository.save(Arrays.asList(ip1, ip2, ip3));
	}
}
