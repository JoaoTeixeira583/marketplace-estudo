package br.com.estudo.marketplaceestudo.evento.infraestrutura;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
}
