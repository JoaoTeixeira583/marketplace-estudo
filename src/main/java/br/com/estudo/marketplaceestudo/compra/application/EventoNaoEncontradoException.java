package br.com.estudo.marketplaceestudo.compra.application;

public class EventoNaoEncontradoException extends RuntimeException {

    public EventoNaoEncontradoException(String mensagem){
        super(mensagem);
    }

}
