package br.com.estudo.marketplaceestudo.compra;



import java.util.Objects;
import java.util.UUID;

public record CompraId(UUID id) {

    // e verificar se o id e nullo
    public CompraId {
        Objects.requireNonNull(id, "Um id não pode ser null");
    }

    // gerar o id
    public CompraId(){
        this(UUID.randomUUID());
    }
}
