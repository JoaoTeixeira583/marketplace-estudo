package br.com.estudo.marketplaceestudo.compra;

import br.com.estudo.marketplaceestudo.cadastro.ClienteId;

import java.util.List;
import java.util.Optional;

public interface CompraRepositorioDominio {

    void salvar(Compra compra);
    List<Compra> listarPorCliente(ClienteId  clienteId);
    Optional<Compra> buscarPorId(CompraId id);
}
