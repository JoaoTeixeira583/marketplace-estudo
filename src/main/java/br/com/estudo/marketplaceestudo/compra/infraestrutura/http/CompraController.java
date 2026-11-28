package br.com.estudo.marketplaceestudo.compra.infraestrutura.http;

import br.com.estudo.marketplaceestudo.cadastro.ClienteId;
import br.com.estudo.marketplaceestudo.compra.Compra;
import br.com.estudo.marketplaceestudo.compra.CompraId;
import br.com.estudo.marketplaceestudo.compra.application.CancelarCompraUseCase;
import br.com.estudo.marketplaceestudo.compra.application.ComprarIngressoUseCase;
import br.com.estudo.marketplaceestudo.compra.application.ListarComprasPorClienteUseCase;
import br.com.estudo.marketplaceestudo.compra.infraestrutura.http.dto.ComprarIngressoRequisicao;
import br.com.estudo.marketplaceestudo.evento.EventoId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "compras")
public class CompraController {

    private final ComprarIngressoUseCase comprarIngressoUseCase;
    private final ListarComprasPorClienteUseCase listarComprasPorClienteUseCase;
    private final CancelarCompraUseCase cancelarCompraUseCase;

    public CompraController(ComprarIngressoUseCase  comprarIngressoUseCase,
                            ListarComprasPorClienteUseCase listarComprasPorClienteUseCase,
                            CancelarCompraUseCase cancelarCompraUseCase
                            ) {
        this.comprarIngressoUseCase = comprarIngressoUseCase;
        this.listarComprasPorClienteUseCase = listarComprasPorClienteUseCase;
        this.cancelarCompraUseCase = cancelarCompraUseCase;
    }

    @PostMapping
    public ResponseEntity<Compra> comprarIngresso(@RequestBody ComprarIngressoRequisicao comprarIngressoRequisicao) {

        // converter os UUIDS
        var idCliente = new ClienteId(comprarIngressoRequisicao.clienteId());
        var idEvento = new EventoId(comprarIngressoRequisicao.eventoId());

        var compra = comprarIngressoUseCase.executar(idCliente, idEvento);

        return ResponseEntity.status(HttpStatus.CREATED).body(compra);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<List<Compra>> listarComprasPorCliente(@PathVariable UUID id) {
        var listarCliente = listarComprasPorClienteUseCase.executar(new ClienteId(id));

        return ResponseEntity.ok().body(listarCliente);
    }

    @PostMapping("/{id}/cancelamento")
    public ResponseEntity<Compra> cancelarCompra(@PathVariable UUID id) {
        var idCompra = new CompraId(id);
        var compra = cancelarCompraUseCase.executar(idCompra);

        return ResponseEntity.ok(compra);
    }
}
