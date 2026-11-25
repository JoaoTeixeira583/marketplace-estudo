package br.com.estudo.marketplaceestudo.cadastro;

import lombok.Getter;

@Getter
public class Cliente {

    private ClienteId id;
    private String nome;
    private String email;

    // cliente existente
    public Cliente(ClienteId id, String nome, String email) {

        if(id == null){
            throw new IllegalArgumentException("O id deve ser obrigatório");
        }
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("O nome deve ser preenchido");
        }
        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("O email deve ser preenchido");
        }

        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    //para criar um cliente novo
    public Cliente(String nome, String email) {
        this(new ClienteId(), nome, email);
    }
}
