package br.com.estudo.marketplaceestudo.evento.infraestrutura.http;


import br.com.estudo.marketplaceestudo.evento.Evento;
import br.com.estudo.marketplaceestudo.evento.EventoId;
import br.com.estudo.marketplaceestudo.evento.application.BuscarEventoPorIdUseCase;
import br.com.estudo.marketplaceestudo.evento.application.CriarEventoUseCase;
import br.com.estudo.marketplaceestudo.evento.application.VenderIngressoUseCase;
import br.com.estudo.marketplaceestudo.evento.infraestrutura.http.dto.CriarEventoRequisicao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final CriarEventoUseCase criarEventoUseCase;
    private final BuscarEventoPorIdUseCase buscarEventoPorIdUseCase;
    private final VenderIngressoUseCase venderIngressoUseCase;


    public EventoController(CriarEventoUseCase criarEventoUseCase,
                            BuscarEventoPorIdUseCase buscarEventoPorIdUseCase,
                            VenderIngressoUseCase venderIngressoUseCase) {
        this.criarEventoUseCase = criarEventoUseCase;
        this.buscarEventoPorIdUseCase = buscarEventoPorIdUseCase;
        this.venderIngressoUseCase = venderIngressoUseCase;
    }

    @PostMapping
    public ResponseEntity<EventoId> salvarEvento(@RequestBody CriarEventoRequisicao evento){
        var eventoIdCriado = criarEventoUseCase.executar(evento.nome(), evento.local(), evento.capacidade());

        return ResponseEntity.status(HttpStatus.CREATED).body(eventoIdCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> buscarEventoId(@PathVariable UUID id){
        var resultadoBusca = buscarEventoPorIdUseCase.executar(new EventoId(id));

        if(resultadoBusca.isEmpty()){
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(resultadoBusca.get());
    }

    @PostMapping("/{id}/ingressos")
    public ResponseEntity<Evento> venderIngresso(@PathVariable UUID id){
        var resultadoBusca = venderIngressoUseCase.executar(new EventoId(id));

        if(resultadoBusca.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(resultadoBusca.get());
    }
}
