package br.com.estudo.marketplaceestudo.evento.infraestrutura.http;


import br.com.estudo.marketplaceestudo.evento.Evento;
import br.com.estudo.marketplaceestudo.evento.EventoId;
import br.com.estudo.marketplaceestudo.evento.application.*;
import br.com.estudo.marketplaceestudo.evento.infraestrutura.http.dto.CriarEventoRequisicao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eventos")
@RequiredArgsConstructor
public class EventoController {

    private final CriarEventoUseCase criarEventoUseCase;
    private final BuscarEventoPorIdUseCase buscarEventoPorIdUseCase;
    private final VenderIngressoUseCase venderIngressoUseCase;
    private final ListarEventoUseCase listarEventoUseCase;
    private final ExcluirEventoUseCase excluirEventoUseCase;
    private final CancelarEventoUseCase cancelarEventoUseCase;



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

    @GetMapping
    public ResponseEntity<List<Evento>> listarEvento(){
        var listarEvento = listarEventoUseCase.executar();
        return ResponseEntity.ok(listarEvento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable UUID id){
        var eventoExcluir = excluirEventoUseCase.executar(new EventoId(id));

        if(eventoExcluir == false){
           return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/cancelamento")
    public ResponseEntity<Evento> cancelarEvento(@PathVariable UUID id){
         var resultadoCancelamento = cancelarEventoUseCase.executar(new EventoId(id));

         if(resultadoCancelamento.isEmpty()){
             return ResponseEntity.notFound().build();
         }

         return ResponseEntity.ok(resultadoCancelamento.get());
    }
}
