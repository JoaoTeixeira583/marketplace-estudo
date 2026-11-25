package br.com.estudo.marketplaceestudo.cadastro;

import java.util.Objects;
import java.util.UUID;

public record ClienteId(UUID id) {

    // para fazer a validação do id
    public ClienteId {
        Objects.requireNonNull(id, "Um id não pode ser null");
    }

    // para gerar o id
    public ClienteId() {
        this(UUID.randomUUID());
    }
}
