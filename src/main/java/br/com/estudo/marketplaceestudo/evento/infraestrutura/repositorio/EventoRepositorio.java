package br.com.estudo.marketplaceestudo.evento.infraestrutura.repositorio;


import br.com.estudo.marketplaceestudo.evento.infraestrutura.EventoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventoRepositorio extends JpaRepository<EventoEntidade, UUID> {
}
