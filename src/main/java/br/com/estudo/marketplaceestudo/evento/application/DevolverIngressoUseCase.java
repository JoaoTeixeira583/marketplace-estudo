package br.com.estudo.marketplaceestudo.evento.application;

import br.com.estudo.marketplaceestudo.evento.EventoId;
import br.com.estudo.marketplaceestudo.evento.EventoRepositorioDominio;
import org.springframework.stereotype.Service;

@Service
public class DevolverIngressoUseCase {

    private final EventoRepositorioDominio eventoRepositorioDominio;

    public DevolverIngressoUseCase(EventoRepositorioDominio eventoRepositorioDominio){
        this.eventoRepositorioDominio = eventoRepositorioDominio;
    }

    public void executar(EventoId id){
        var eventoEncontrado = eventoRepositorioDominio.buscarEventoPorId(id);
        if(eventoEncontrado.isEmpty()){
            throw new EventoNaoEncontradoException("Evento não encontrado para o ID informado.");
        }

        var evento = eventoEncontrado.get();
        evento.devolverIngresso();
        eventoRepositorioDominio.salvar(evento);
    }

}
