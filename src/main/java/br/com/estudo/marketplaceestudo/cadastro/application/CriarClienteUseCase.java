package br.com.estudo.marketplaceestudo.cadastro.application;

import br.com.estudo.marketplaceestudo.cadastro.Cliente;
import br.com.estudo.marketplaceestudo.cadastro.ClienteId;
import br.com.estudo.marketplaceestudo.cadastro.ClienteRepositorioDominio;
import org.springframework.stereotype.Service;

@Service
public class CriarClienteUseCase {

    private final ClienteRepositorioDominio clienteRepositorioDominio;

    public CriarClienteUseCase(ClienteRepositorioDominio clienteRepositorioDominio){
        this.clienteRepositorioDominio = clienteRepositorioDominio;
    }

    public ClienteId executar(String nome,String email){
        ClienteId id = new ClienteId();

        Cliente cliente = new Cliente(id,nome,email);

        clienteRepositorioDominio.salvar(cliente);

        return id;
    }
}
