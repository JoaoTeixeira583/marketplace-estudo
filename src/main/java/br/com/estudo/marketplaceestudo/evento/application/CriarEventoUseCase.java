package br.com.estudo.marketplaceestudo.evento.application;


import br.com.estudo.marketplaceestudo.evento.Evento;
import br.com.estudo.marketplaceestudo.evento.EventoId;
import br.com.estudo.marketplaceestudo.evento.EventoRepositorioDominio;
import org.springframework.stereotype.Service;

@Service
public class CriarEventoUseCase {

    private final EventoRepositorioDominio eventoRepositorioDominio;

    public CriarEventoUseCase(EventoRepositorioDominio eventoRepositorio) {
        this.eventoRepositorioDominio = eventoRepositorio;
    }

    public EventoId executar(String nome,String local,int capacidade) {
        // para criar um id
        EventoId id = new EventoId();

        Evento evento = new Evento(id,nome,local,capacidade);

        eventoRepositorioDominio.salvar(evento);

        return id;
    }
}
