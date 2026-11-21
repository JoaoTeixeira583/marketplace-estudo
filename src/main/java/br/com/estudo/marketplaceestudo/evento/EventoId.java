package br.com.estudo.marketplaceestudo.evento;

import org.springframework.util.Assert;

import java.util.Objects;
import java.util.UUID;

// representar um identificador de um evento
public record EventoId(UUID id) {

    // para fazer a validação do id
    public EventoId {
        Objects.requireNonNull(id, "Um id não pode ser null");
    }

    // para gerar o id
    public EventoId() {
        this(UUID.randomUUID());
    }
}
