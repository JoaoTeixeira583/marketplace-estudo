package br.com.estudo.marketplaceestudo.evento.infraestrutura;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        basePackages = "br.com.estudo.marketplaceestudo.evento.infraestrutura.repositorio",
        // como fabrica
        entityManagerFactoryRef = "entityManagerFactory",
        // como gerenciador de transações
        transactionManagerRef = "transactionManager"
)
public class EventoJpaConfiguration {
}
