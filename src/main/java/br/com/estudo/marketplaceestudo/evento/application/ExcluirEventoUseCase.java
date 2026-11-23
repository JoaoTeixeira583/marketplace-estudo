package br.com.estudo.marketplaceestudo.evento.application;

import br.com.estudo.marketplaceestudo.evento.Evento;
import br.com.estudo.marketplaceestudo.evento.EventoId;
import br.com.estudo.marketplaceestudo.evento.EventoRepositorioDominio;
import org.springframework.stereotype.Service;

@Service
public class ExcluirEventoUseCase {

    private final EventoRepositorioDominio eventoRepositorioDominio;

    public ExcluirEventoUseCase(EventoRepositorioDominio eventoRepositorioDominio) {
        this.eventoRepositorioDominio = eventoRepositorioDominio;
    }

    // para fazer a regra dos ingressos do evento
    public boolean executar(EventoId id) {
        var resultadoBusca = eventoRepositorioDominio.buscarEventoPorId(id);

        if (resultadoBusca.isEmpty()) {
            return false;
        }

        Evento evento = resultadoBusca.get();
        evento.validarPodeSerExcluido();
        eventoRepositorioDominio.deletarEventoPorId(id);
        return true;
    }

}
