package br.com.estudo.marketplaceestudo.evento.infraestrutura;

import br.com.estudo.marketplaceestudo.evento.StatusEvento;
import jakarta.persistence.*;
import lombok.*;


import java.util.UUID;

@Entity
@Table(name = "eventos")
@Getter
// O JPA usa o construtor vazio; o acesso protegido evita chamadas diretas por classes fora do pacote.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class EventoEntidade {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String local;

    @Column(nullable = false)
    private int capacidade;

    @Column(nullable = false)
    private int ingressosVendidos;

    // para gravar o enum StatusEvento na coluna status
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusEvento status;

}
