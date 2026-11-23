package br.com.estudo.marketplaceestudo.evento;


import java.util.List;
import java.util.Optional;

public interface EventoRepositorioDominio {

    void salvar(Evento evento);
    Optional<Evento> buscarEventoPorId(EventoId id);
    List<Evento> listarEvento();
    void deletarEventoPorId(EventoId id);

}
