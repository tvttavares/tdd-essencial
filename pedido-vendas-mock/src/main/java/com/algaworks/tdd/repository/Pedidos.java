package com.algaworks.tdd.repository;

import com.algaworks.tdd.model.Pedido;
import com.algaworks.tdd.service.AcaoLancamentoPedido;

public class Pedidos implements AcaoLancamentoPedido {

	@Override
	public void executar(Pedido pedido) {
		System.out.println("Salvando no banco de dados...");
	}

	public Pedido buscarPeloCodigo(Long codigo) {
		// Ele iria no banco de dados buscar pelo codigo
		return new Pedido();
	}
}
