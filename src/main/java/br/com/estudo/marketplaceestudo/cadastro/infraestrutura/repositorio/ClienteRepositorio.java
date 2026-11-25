package br.com.estudo.marketplaceestudo.cadastro.infraestrutura.repositorio;

import br.com.estudo.marketplaceestudo.cadastro.infraestrutura.ClienteEntidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepositorio extends JpaRepository<ClienteEntidade, UUID> {

}
