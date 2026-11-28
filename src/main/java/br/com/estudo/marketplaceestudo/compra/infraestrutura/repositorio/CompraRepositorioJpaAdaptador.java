package br.com.estudo.marketplaceestudo.compra.infraestrutura.repositorio;

import br.com.estudo.marketplaceestudo.cadastro.ClienteId;
import br.com.estudo.marketplaceestudo.compra.Compra;
import br.com.estudo.marketplaceestudo.compra.CompraId;
import br.com.estudo.marketplaceestudo.compra.CompraRepositorioDominio;
import br.com.estudo.marketplaceestudo.compra.infraestrutura.CompraEntidade;
import br.com.estudo.marketplaceestudo.evento.EventoId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// JpaAdpator é a ponte para o dominio ao Jpa
@Repository
public class CompraRepositorioJpaAdaptador implements CompraRepositorioDominio {

    private final CompraRepositorio compraRepositorio;

    public CompraRepositorioJpaAdaptador(CompraRepositorio compraRepositorio){
        this.compraRepositorio = compraRepositorio;
    }

    @Override
    public void salvar(Compra compra) {
        CompraEntidade compraEntidade = new CompraEntidade(compra.getId().id(),compra.getClienteId().id(),compra.getEventoId().id(),compra.getStatus());
        compraRepositorio.save(compraEntidade);
    }

    @Override
    public List<Compra> listarPorCliente(ClienteId clienteId) {
        var listarCliente = compraRepositorio.findByIdCliente(clienteId.id());

         return listarCliente.stream()
                .map(compraEntidade ->
                        new Compra(
                                // para recriar usando os UUIDS da entidade
                                new CompraId(compraEntidade.getIdCompra()),
                                new ClienteId(compraEntidade.getIdCliente()),
                                new EventoId(compraEntidade.getIdEvento()),
                                compraEntidade.getStatus()
                        ))
                .toList();
    }

    @Override
    public Optional<Compra> buscarPorId(CompraId id) {
        return compraRepositorio.findById(id.id())
                .map(compraEntidade ->
                        new Compra(
                                new CompraId(compraEntidade.getIdCompra()),
                                new ClienteId(compraEntidade.getIdCliente()),
                                new EventoId(compraEntidade.getIdEvento()),
                                compraEntidade.getStatus()
                        ));
    }
}
