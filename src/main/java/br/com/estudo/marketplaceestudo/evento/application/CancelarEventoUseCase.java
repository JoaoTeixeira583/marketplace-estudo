package br.com.estudo.marketplaceestudo.evento.application;

import br.com.estudo.marketplaceestudo.evento.Evento;
import br.com.estudo.marketplaceestudo.evento.EventoId;
import br.com.estudo.marketplaceestudo.evento.EventoRepositorioDominio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CancelarEventoUseCase {

    private final EventoRepositorioDominio eventoRepositorioDominio;

    public CancelarEventoUseCase(EventoRepositorioDominio eventoRepositorio) {
        this.eventoRepositorioDominio = eventoRepositorio;
    }

    public Optional<Evento> executar(EventoId id){
        var resultadoEncontrado = eventoRepositorioDominio.buscarEventoPorId(id);

        if(resultadoEncontrado.isEmpty()){
            return Optional.empty();
        }

        Evento evento = resultadoEncontrado.get();
        // para cancelar o evento
        evento.cancelar();
        eventoRepositorioDominio.salvar(evento);
        return Optional.of(evento);
    }
}
