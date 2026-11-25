package br.com.estudo.marketplaceestudo.cadastro.infraestrutura.http;

import br.com.estudo.marketplaceestudo.cadastro.ClienteId;
import br.com.estudo.marketplaceestudo.cadastro.application.CriarClienteUseCase;
import br.com.estudo.marketplaceestudo.cadastro.infraestrutura.http.dto.CriarClienteRequisicao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final CriarClienteUseCase criarClienteUseCase;

    public ClienteController(CriarClienteUseCase criarClienteUseCase) {
        this.criarClienteUseCase = criarClienteUseCase;
    }

    @PostMapping
    public ResponseEntity<ClienteId> salvarCliente(@RequestBody CriarClienteRequisicao criarClienteRequisicao) {
        var clienteIdCriado = criarClienteUseCase.executar(criarClienteRequisicao.nome(), criarClienteRequisicao.email());

        return ResponseEntity.status(HttpStatus.CREATED).body(clienteIdCriado);
    }
}
