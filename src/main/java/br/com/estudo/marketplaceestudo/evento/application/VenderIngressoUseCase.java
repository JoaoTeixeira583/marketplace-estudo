package br.com.estudo.marketplaceestudo.evento.application;

import br.com.estudo.marketplaceestudo.evento.Evento;
import br.com.estudo.marketplaceestudo.evento.EventoId;
import br.com.estudo.marketplaceestudo.evento.EventoRepositorioDominio;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VenderIngressoUseCase {

    private final EventoRepositorioDominio eventoRepositorioDominio;

    public VenderIngressoUseCase(EventoRepositorioDominio eventoRepositorioDominio) {
        this.eventoRepositorioDominio = eventoRepositorioDominio;
    }

    public Optional<Evento> executar(EventoId id){
        var resultadoEncontrado = eventoRepositorioDominio.buscarEventoPorId(id);

        if(resultadoEncontrado.isEmpty()){
            return Optional.empty();
        }

        // para pegar o evento do resultado do id
        Evento evento = resultadoEncontrado.get();
        // para aplicar o metodo
        evento.venderIngresso();
        // salvar no repositorio
        eventoRepositorioDominio.salvar(evento);

        return Optional.of(evento);
    }
}
