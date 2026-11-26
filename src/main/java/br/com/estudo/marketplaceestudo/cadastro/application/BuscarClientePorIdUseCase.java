package br.com.estudo.marketplaceestudo.cadastro.application;

import br.com.estudo.marketplaceestudo.cadastro.Cliente;
import br.com.estudo.marketplaceestudo.cadastro.ClienteId;
import br.com.estudo.marketplaceestudo.cadastro.ClienteRepositorioDominio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarClientePorIdUseCase {

    private final ClienteRepositorioDominio clienteRepositorioDominio;

    public BuscarClientePorIdUseCase(ClienteRepositorioDominio clienteRepositorioDominio){
        this.clienteRepositorioDominio = clienteRepositorioDominio;
    }

    public Optional<Cliente> executar(ClienteId id){
        var clienteEncontrado = clienteRepositorioDominio.buscarClientePorId(id);

        return clienteEncontrado;
    }
}
