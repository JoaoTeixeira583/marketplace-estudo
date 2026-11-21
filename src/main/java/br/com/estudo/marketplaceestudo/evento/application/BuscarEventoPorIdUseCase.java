package br.com.estudo.marketplaceestudo.evento.application;

import br.com.estudo.marketplaceestudo.evento.Evento;
import br.com.estudo.marketplaceestudo.evento.EventoId;
import br.com.estudo.marketplaceestudo.evento.EventoRepositorioDominio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BuscarEventoPorIdUseCase {

    private final EventoRepositorioDominio eventoRepositorioDominio;

    public BuscarEventoPorIdUseCase(EventoRepositorioDominio eventoRepositorio) {
        this.eventoRepositorioDominio = eventoRepositorio;
    }

    public Optional<Evento> executar(EventoId id) {
        var eventoIdencontrado = eventoRepositorioDominio.buscarEventoPorId(id);

        return eventoIdencontrado;
    }


}
