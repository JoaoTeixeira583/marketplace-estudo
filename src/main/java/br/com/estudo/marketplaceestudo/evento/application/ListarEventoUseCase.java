package br.com.estudo.marketplaceestudo.evento.application;

import br.com.estudo.marketplaceestudo.evento.Evento;
import br.com.estudo.marketplaceestudo.evento.EventoRepositorioDominio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarEventoUseCase {

    private final EventoRepositorioDominio eventoRepositorioDominio;

    public ListarEventoUseCase(EventoRepositorioDominio  eventoRepositorioDominio) {
        this.eventoRepositorioDominio = eventoRepositorioDominio;
    }

    public List<Evento> executar(){
        return eventoRepositorioDominio.listarEvento();
    }
}
