package br.com.estudo.marketplaceestudo.cadastro;

import java.util.Optional;

public interface ClienteRepositorioDominio {

    void salvar(Cliente cliente);

    Optional<Cliente> buscarClientePorId(ClienteId id);
}
