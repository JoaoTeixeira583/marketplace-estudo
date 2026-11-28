package br.com.estudo.marketplaceestudo.compra;

import br.com.estudo.marketplaceestudo.cadastro.ClienteId;

import java.util.List;

public interface CompraRepositorioDominio {

    void salvar(Compra compra);
    List<Compra> listarPorCliente(ClienteId  clienteId);

}
