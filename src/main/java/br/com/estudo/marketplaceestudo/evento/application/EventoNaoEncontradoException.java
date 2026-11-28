package br.com.estudo.marketplaceestudo.evento.application;

public class EventoNaoEncontradoException extends RuntimeException {

    public EventoNaoEncontradoException(String mensagem){
        super(mensagem);
    }

}
