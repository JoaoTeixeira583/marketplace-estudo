package br.com.estudo.marketplaceestudo.compra;

import br.com.estudo.marketplaceestudo.cadastro.ClienteId;
import br.com.estudo.marketplaceestudo.evento.EventoId;
import lombok.Getter;

@Getter
public class Compra {

    private CompraId id;
    private ClienteId clienteId;
    private EventoId eventoId;

    public Compra(CompraId id, ClienteId clienteId, EventoId eventoId){

        if(id == null){
            throw new IllegalArgumentException("O ID da compra é obrigatório.");
        }
        if(clienteId == null){
            throw new IllegalArgumentException("O ID do cliente é obrigatório.");
        }
        if(eventoId == null){
            throw new IllegalArgumentException("O ID do evento é obrigatório.");
        }

        this.id = id;
        this.clienteId = clienteId;
        this.eventoId = eventoId;
    }
}
