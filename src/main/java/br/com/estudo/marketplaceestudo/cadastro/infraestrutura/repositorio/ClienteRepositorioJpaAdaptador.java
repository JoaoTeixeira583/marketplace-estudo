package br.com.estudo.marketplaceestudo.cadastro.infraestrutura.repositorio;

import br.com.estudo.marketplaceestudo.cadastro.Cliente;
import br.com.estudo.marketplaceestudo.cadastro.ClienteRepositorioDominio;
import br.com.estudo.marketplaceestudo.cadastro.infraestrutura.ClienteEntidade;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class ClienteRepositorioJpaAdaptador implements ClienteRepositorioDominio {

   private final ClienteRepositorio clienteRepositorio;

   public ClienteRepositorioJpaAdaptador(ClienteRepositorio clienteRepositorio) {
       this.clienteRepositorio = clienteRepositorio;
   }

    @Override
    public void salvar(Cliente cliente) {
        ClienteEntidade clienteEntidade = new ClienteEntidade(cliente.getId().id(),cliente.getNome(),cliente.getEmail());
        clienteRepositorio.save(clienteEntidade);
    }
}
