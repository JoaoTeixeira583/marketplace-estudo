package br.com.estudo.marketplaceestudo.compra;

import br.com.estudo.marketplaceestudo.cadastro.ClienteId;
import br.com.estudo.marketplaceestudo.evento.EventoId;
import lombok.Getter;

@Getter
public class Compra {

    private CompraId id;
    private ClienteId clienteId;
    private EventoId eventoId;
    private StatusCompra status;

    public Compra(CompraId id, ClienteId clienteId, EventoId eventoId,StatusCompra status) {

        if(id == null){
            throw new IllegalArgumentException("O ID da compra é obrigatório.");
        }
        if(clienteId == null){
            throw new IllegalArgumentException("O ID do cliente é obrigatório.");
        }
        if(eventoId == null){
            throw new IllegalArgumentException("O ID do evento é obrigatório.");
        }
        if(status == null){
            throw new IllegalArgumentException("O status da compra é obrigatório.");
        }

        this.id = id;
        this.clienteId = clienteId;
        this.eventoId = eventoId;
        this.status = status;
    }

    public Compra(CompraId id,ClienteId clienteId,EventoId eventoId){
        this(id,clienteId,eventoId,StatusCompra.ATIVA);
    }

    // metodo para cancelar a compra de um ingrsso
    public void cancelar(){
        if(status == StatusCompra.CANCELADA){
            throw new IllegalStateException("Esta compra já está cancelada.");
        }
        this.status = StatusCompra.CANCELADA;
    }
}
