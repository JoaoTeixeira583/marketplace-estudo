package br.com.estudo.marketplaceestudo.evento.infraestrutura.repositorio;

import br.com.estudo.marketplaceestudo.evento.Evento;
import br.com.estudo.marketplaceestudo.evento.EventoId;
import br.com.estudo.marketplaceestudo.evento.EventoRepositorioDominio;
import br.com.estudo.marketplaceestudo.evento.infraestrutura.EventoEntidade;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// para conectar o repositorio do dominio para o repositorio do banco de dados que esta vinculado a JpaRepository
@Repository // para o spring reconhecer como um componente e disponibilizá-lo para injeção
public class EventoRepositorioJpaAdaptador implements EventoRepositorioDominio {

    private final EventoRepositorio eventoRepositorio;

    public EventoRepositorioJpaAdaptador(EventoRepositorio eventoRepositorio) {
        this.eventoRepositorio = eventoRepositorio;
    }

    @Override
    public void salvar(Evento evento) {
       EventoEntidade eventoEntidade = new EventoEntidade(evento.getId().id(), evento.getNome(), evento.getLocal(),evento.getCapacidade(),evento.getIngressosVendidos());
       eventoRepositorio.save(eventoEntidade);
    }

    @Override
    public Optional<Evento> buscarEventoPorId(EventoId id) {
        var resultadoBusca = eventoRepositorio.findById(id.id());

        // para converter o objeto evenento do domain
        return resultadoBusca.map(entidade -> new Evento(
                new EventoId(entidade.getId()),
                entidade.getNome(),
                entidade.getLocal(),
                entidade.getCapacidade(),
                entidade.getIngressosVendidos()
        ));
    }
}
