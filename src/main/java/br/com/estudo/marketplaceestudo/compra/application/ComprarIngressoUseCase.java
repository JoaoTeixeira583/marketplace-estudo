package br.com.estudo.marketplaceestudo.compra.application;

import br.com.estudo.marketplaceestudo.cadastro.ClienteId;
import br.com.estudo.marketplaceestudo.cadastro.application.BuscarClientePorIdUseCase;
import br.com.estudo.marketplaceestudo.cadastro.application.ClienteNaoEncontradoException;
import br.com.estudo.marketplaceestudo.compra.Compra;
import br.com.estudo.marketplaceestudo.compra.CompraId;
import br.com.estudo.marketplaceestudo.compra.CompraRepositorioDominio;
import br.com.estudo.marketplaceestudo.evento.EventoId;
import br.com.estudo.marketplaceestudo.evento.application.EventoNaoEncontradoException;
import br.com.estudo.marketplaceestudo.evento.application.VenderIngressoUseCase;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ComprarIngressoUseCase {

    private final CompraRepositorioDominio compraRepositorioDominio;
    private final BuscarClientePorIdUseCase buscarClientePorIdUseCase;
    private final VenderIngressoUseCase venderIngressoUseCase;

    public ComprarIngressoUseCase(CompraRepositorioDominio  compraRepositorioDominio,
                                  BuscarClientePorIdUseCase buscarClientePorIdUseCase,
                                  VenderIngressoUseCase venderIngressoUseCase
                                  ){
        this.compraRepositorioDominio = compraRepositorioDominio;
        this.buscarClientePorIdUseCase = buscarClientePorIdUseCase;
        this.venderIngressoUseCase = venderIngressoUseCase;
    }

    // para fazer uma compra de um ingresso
    // para selecionar o PostegerSql
    @Transactional(transactionManager = "transactionManager")
    public Compra executar(ClienteId clienteId, EventoId eventoId){
        // Parte do cliente
        var clienteEncontrado = buscarClientePorIdUseCase.executar(clienteId);
        if(clienteEncontrado.isEmpty()){
            throw new ClienteNaoEncontradoException("Cliente não encontrado para o ID informado.");
        }
        // Parte do evento
        var venderIngressos = venderIngressoUseCase.executar(eventoId);
        if(venderIngressos.isEmpty()){
            throw new EventoNaoEncontradoException("Evento não encontrado para o ID informado.");
        }

        var compra = new Compra(new CompraId(),clienteId,eventoId);
        compraRepositorioDominio.salvar(compra);
        return compra;
    }
}
