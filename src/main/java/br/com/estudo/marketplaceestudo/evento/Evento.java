package br.com.estudo.marketplaceestudo.evento;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Evento {

    private EventoId id;
    private String nome;
    private String local;
    private int capacidade;
    private int ingressosVendidos;

    public Evento(EventoId id, String nome, String local, int capacidade){
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

        this.id = id;
        this.nome = nome;
        this.local = local;
        this.capacidade = capacidade;
        this.ingressosVendidos = 0;
    }

    public Evento(EventoId id, String nome, String local, int capacidade, int ingressosVendidos){

        this(id, nome, local, capacidade);

        if(ingressosVendidos <0 || ingressosVendidos > capacidade){
            throw new IllegalArgumentException("A quantidade de ingressos vendidos deve estar entre zero e a capacidade do evento.");
        }

        this.ingressosVendidos = ingressosVendidos;
    }


    // para vender ingressos
    public void venderIngresso(){
        if(ingressosVendidos >= this.capacidade){
            throw new IllegalStateException("Não há ingressos disponíveis para este evento.");
        }
        ingressosVendidos += 1;
    }

    // para ver quantos ingressos ainda estão disponíveis
    public int getIngressosDisponiveis(){
        return capacidade - ingressosVendidos;
    }
}
