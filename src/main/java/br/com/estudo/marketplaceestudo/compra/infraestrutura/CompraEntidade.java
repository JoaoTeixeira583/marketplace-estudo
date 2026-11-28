package br.com.estudo.marketplaceestudo.compra.infraestrutura;

import br.com.estudo.marketplaceestudo.compra.StatusCompra;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@Table(name = "compras")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CompraEntidade {

    @Id
    private UUID idCompra;

    @Column(nullable = false)
    private UUID idCliente;

    @Column(nullable = false)
    private UUID idEvento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusCompra status;
}
