package br.com.estudo.marketplaceestudo.compra.infraestrutura.http.dto;

import java.util.UUID;

public record ComprarIngressoRequisicao(UUID clienteId,UUID eventoId) {
}
