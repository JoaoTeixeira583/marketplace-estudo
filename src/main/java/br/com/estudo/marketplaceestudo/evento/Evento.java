package br.com.estudo.marketplaceestudo.evento;

import lombok.Getter;


@Getter
public class Evento {

    private EventoId id;
    private String nome;
    private String local;
    private int capacidade;
    private int ingressosVendidos;
    private StatusEvento status;

    public Evento(EventoId id, String nome, String local, int capacidade,int ingressosVendidos,StatusEvento status){
        // para capacidade tem que ser maior que zero
        if(capacidade <= 0){
           throw new IllegalArgumentException("A capacidade deve ser positiva");
        }
        // para nome não ser null ou vazio
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("O nome deve ser preenchido");
        }
        // para local não ser null ou vazio
        if(local == null || local.isBlank()){
            throw new IllegalArgumentException("O local deve ser preenchido");
        }
        // para id não ser null ou vazio
        if(id == null){
            throw new IllegalArgumentException("O id deve ser obrigatório");
        }
        if(status == null){
            throw new IllegalArgumentException("O status deve ser preenchido");
        }
        if(ingressosVendidos <0 || ingressosVendidos > capacidade){
            throw new IllegalArgumentException("A quantidade de ingressos vendidos deve estar entre zero e a capacidade do evento.");
        }

        this.id = id;
        this.nome = nome;
        this.local = local;
        this.capacidade = capacidade;
        this.status = status;
        this.ingressosVendidos = ingressosVendidos;
    }

    // para criar um evento com ativo e os ingressos vendidos e 0
    public Evento(EventoId id, String nome, String local, int capacidade) {
        this(id, nome, local, capacidade, 0, StatusEvento.ATIVO);
    }

    // para vender ingressos
    public void venderIngresso(){
        // para configurar o status e cancelado
        if(status == StatusEvento.CANCELADO){
            throw new IllegalStateException("Não é possível vender ingressos para um evento cancelado.");
        }

        if(ingressosVendidos >= this.capacidade){
            throw new IllegalStateException("Não há ingressos disponíveis para este evento.");
        }
        ingressosVendidos += 1;
    }

    // para ver quantos ingressos ainda estão disponíveis
    public int getIngressosDisponiveis(){
        return capacidade - ingressosVendidos;
    }

    public void validarPodeSerExcluido(){
        if(ingressosVendidos == 0){
            return;
        }

        throw new IllegalStateException("Não é possível excluir um evento que já possui ingressos vendidos.");
    }

    //metodo para cancelar um evento
    public void cancelar(){
        this.status = StatusEvento.CANCELADO;
    }
}
