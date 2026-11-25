package br.com.estudo.marketplaceestudo.cadastro.infraestrutura;

import com.zaxxer.hikari.HikariDataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.boot.jpa.autoconfigure.JpaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.LinkedHashMap;

// para conectar com o banco de dados no MySql
@Configuration
@EnableJpaRepositories(
        basePackages = "br.com.estudo.marketplaceestudo.cadastro.infraestrutura.repositorio",
        // como fabrica
        entityManagerFactoryRef = "cadastroEntityManagerFactory",
        // como gerenciador de transações
        transactionManagerRef = "cadastroTransactionManager"
)
public class CadastroJpaConfiguration {

    // para criar a conexão com o mySql
    @Qualifier("cadastro")
    @ConfigurationProperties("cadastro.datasource")
    @Bean(defaultCandidate = false)
    public DataSourceProperties cadastroDataSourceProperties() {
       return new DataSourceProperties();
    }

    // para fazer a conexão com o mySql
    @Bean(defaultCandidate = false)
    @Qualifier("cadastro")
    @ConfigurationProperties("cadastro.datasource.configuration")
    public HikariDataSource cadastroDataSource(@Qualifier("cadastro") DataSourceProperties cadastro) {
       // para construir um hikari para fazer uma conexão
        return cadastro.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    // criar o objeto de configurações jpa do modulo cadastro
    @Bean(defaultCandidate = false) // para não usar como configuração padrão
    @Qualifier("cadastro") // para não confudir com os outros bancos
    @ConfigurationProperties("cadastro.jpa") // para pegar do application proprietes
    public JpaProperties cadastroJpaProperties(){
          return new JpaProperties();
    }

    // para conectar a entity com o jpa
    // uma fábrica de EntityManager para o banco de cadastro
    @Bean(defaultCandidate = false)
    @Qualifier("cadastro")
    public LocalContainerEntityManagerFactoryBean cadastroEntityManagerFactory(@Qualifier("cadastro") DataSource dataSource, @Qualifier("cadastro") JpaProperties jpaProperties){
        EntityManagerFactoryBuilder entityManager = new EntityManagerFactoryBuilder(
                new HibernateJpaVendorAdapter(),
                dataSource1 -> new LinkedHashMap<>(jpaProperties.getProperties()),
                null
        );

        return entityManager.dataSource(dataSource)
                // para procurar nesse caminho a entidade
                .packages("br.com.estudo.marketplaceestudo.cadastro.infraestrutura")
                // indentifica a unidade como cadastro
                .persistenceUnit("cadastro")
                .build();

    }

    // para criar o gerenciador de transações do banco de cadastro
    // vai conferir se as operaçãoes do cadastro de certo ou se não der vai ser revertida
    @Bean(defaultCandidate = false)
    @Qualifier("cadastro")
    public JpaTransactionManager cadastroTransactionManager(@Qualifier("cadastro") EntityManagerFactory entityManagerFactory){
          // criar o JpaTransactionManager
          JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(
                  entityManagerFactory
          );

          return jpaTransactionManager;
    }

}
