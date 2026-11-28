package br.com.estudo.marketplaceestudo.compra.application;

public class ClienteNaoEncontradoException extends RuntimeException{

    // para receber a mensagem na classe pai
    public ClienteNaoEncontradoException(String mensagem){
        super(mensagem);
    }

}
