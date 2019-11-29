package com.algaworks.tdd.service;

import java.util.List;

import com.algaworks.tdd.model.Pedido;
import com.algaworks.tdd.model.StatusPedido;
import com.algaworks.tdd.repository.Pedidos;

public class PedidoService {

	private List<AcaoLancamentoPedido> acoes;
	private Pedidos pedidos;

	public PedidoService(Pedidos pedidos, List<AcaoLancamentoPedido> acoes) {
		this.acoes = acoes;
		this.pedidos = pedidos;
	}

	public double lancar(Pedido pedido) {
		double imposto = pedido.getValor() * 0.1;

		acoes.forEach(a -> a.executar(pedido));

		return imposto;
	}

	public Pedido pagar(Long codigoPedido) {
		Pedido pedido = pedidos.buscarPeloCodigo(codigoPedido);

		if (!pedido.getStatus().equals(StatusPedido.PENDENTE)) {
			throw new StatusPedidoInvalidoException();
		}

		pedido.setStatus(StatusPedido.PAGO);
		return pedido;
	}

}
