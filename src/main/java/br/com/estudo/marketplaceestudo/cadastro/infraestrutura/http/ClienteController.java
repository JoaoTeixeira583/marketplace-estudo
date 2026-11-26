package br.com.estudo.marketplaceestudo.cadastro.infraestrutura.http;

import br.com.estudo.marketplaceestudo.cadastro.Cliente;
import br.com.estudo.marketplaceestudo.cadastro.ClienteId;
import br.com.estudo.marketplaceestudo.cadastro.application.BuscarClientePorIdUseCase;
import br.com.estudo.marketplaceestudo.cadastro.application.CriarClienteUseCase;
import br.com.estudo.marketplaceestudo.cadastro.infraestrutura.http.dto.CriarClienteRequisicao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final CriarClienteUseCase criarClienteUseCase;
    private final BuscarClientePorIdUseCase buscarClientePorIdUseCase;

    public ClienteController(CriarClienteUseCase criarClienteUseCase,
                             BuscarClientePorIdUseCase buscarClientePorIdUseCase) {
        this.criarClienteUseCase = criarClienteUseCase;
        this.buscarClientePorIdUseCase = buscarClientePorIdUseCase;

    }

    @PostMapping
    public ResponseEntity<ClienteId> salvarCliente(@RequestBody CriarClienteRequisicao criarClienteRequisicao) {
        var clienteIdCriado = criarClienteUseCase.executar(criarClienteRequisicao.nome(), criarClienteRequisicao.email());

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteIdCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClienteId(@PathVariable UUID id) {
        var clienteEncontrado = buscarClientePorIdUseCase.executar(new ClienteId(id));

        if(clienteEncontrado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // e oq retornar a mensagem da reposta da api
        return ResponseEntity.ok(clienteEncontrado.get());
    }
}
