package br.com.estudo.marketplaceestudo.compra.infraestrutura.repositorio;


import br.com.estudo.marketplaceestudo.compra.infraestrutura.CompraEntidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CompraRepositorio extends JpaRepository<CompraEntidade, UUID> {

    List<CompraEntidade> findByIdCliente(UUID idCliente);

}
