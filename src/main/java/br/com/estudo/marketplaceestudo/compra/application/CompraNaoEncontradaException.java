package br.com.estudo.marketplaceestudo.compra.application;

public class CompraNaoEncontradaException extends RuntimeException{

    public CompraNaoEncontradaException(String mensagem){
        super(mensagem);
    }

}
