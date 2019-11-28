package com.algaworks;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PedidoTest {

	private PedidoBuilder pedido;

	@Before
	public void setup() {
		pedido = new PedidoBuilder();
	}

	private void assertResumoPedido(double valorTotal, double desconto) {
		ResumoPedido resumoPedido = pedido.construir().resumo();

//		assertEquals(valorTotal, resumoPedido.getValorTotal(), 0.0001);
//		assertEquals(desconto, resumoPedido.getDesconto(), 0.0001);
		assertEquals(new ResumoPedido(valorTotal, desconto), resumoPedido);
	}

	@Test
	public void devePermitirAdicionarUmItemNoPedido() throws Exception {
//		pedido.adicionarItem(new ItemPedido("Sabonete", 3.0, 10));
		pedido.comItem(3.0, 10);
	}

	@Test
	public void deveCalcularValorTotalEDescontoParaPedidoVazio() throws Exception {
		assertResumoPedido(0.0, 0.0);
	}

	@Test
	public void deveCalcularResumoParaUmItemSemDesconto() throws Exception {
//		pedido.adicionarItem(new ItemPedido("Sabonete", 5.0, 5));
		pedido.comItem(5.0, 5);
		assertResumoPedido(25.0, 0.0);
	}

	@Test
	public void deveCalcularResumoParaDoisItensSemDesconto() throws Exception {
//		pedido.adicionarItem(new ItemPedido("Sabonete", 3.0, 3));
//		pedido.adicionarItem(new ItemPedido("Pasta dental", 7.0, 3));
		pedido.comItem(3.0, 3).comItem(7.0, 3);

		assertResumoPedido(30.0, 0.0);
	}

	@Test
	public void deveAplicarDescontoNa1aFaixa() throws Exception {
//		pedido.adicionarItem(new ItemPedido("Creme", 20.0, 20));
		pedido.comItem(20.0, 20);
		assertResumoPedido(400.0, 16.0);
	};

	@Test
	public void deveAplicarDescontoNa2aFaixa() throws Exception {
//		pedido.adicionarItem(new ItemPedido("Shampoo", 15.0, 30));
//		pedido.adicionarItem(new ItemPedido("Óleo", 15.0, 30));
		pedido.comItem(15.0, 30).comItem(15.0, 30);
		assertResumoPedido(900.0, 54.0);
	}

	@Test
	public void deveAplicarDescontoNa3aFaixa() throws Exception {
//		pedido.adicionarItem(new ItemPedido("Creme", 15.0, 30));
//		pedido.adicionarItem(new ItemPedido("Óleo", 15.0, 30));
//		pedido.adicionarItem(new ItemPedido("Shampoo", 10.0, 30));
		pedido.comItem(15.0, 30).comItem(15.0, 30).comItem(10.0, 30);

		assertResumoPedido(1200.0, 96.0);
	}

	@Test(expected = QuantidadeItensInvalidaException.class)
	public void naoAceitarPedidosComItensComQuantidadesNegativas() throws Exception {
		pedido.comItem(0.0, -10);
	}

}