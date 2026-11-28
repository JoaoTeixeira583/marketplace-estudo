package br.com.estudo.marketplaceestudo.compra.application;

import br.com.estudo.marketplaceestudo.cadastro.ClienteId;
import br.com.estudo.marketplaceestudo.cadastro.application.BuscarClientePorIdUseCase;
import br.com.estudo.marketplaceestudo.cadastro.application.ClienteNaoEncontradoException;
import br.com.estudo.marketplaceestudo.compra.Compra;
import br.com.estudo.marketplaceestudo.compra.CompraRepositorioDominio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarComprasPorClienteUseCase {

    private final CompraRepositorioDominio compraRepositorioDominio;
    private final BuscarClientePorIdUseCase buscarClientePorIdUseCase;

    public ListarComprasPorClienteUseCase(CompraRepositorioDominio compraRepositorioDominio,
                                          BuscarClientePorIdUseCase buscarClientePorIdUseCase
                                          ){
        this.compraRepositorioDominio = compraRepositorioDominio;
        this.buscarClientePorIdUseCase = buscarClientePorIdUseCase;
    }

    public List<Compra> executar(ClienteId id){
        var clienteEncontrado = buscarClientePorIdUseCase.executar(id);

        if(clienteEncontrado.isEmpty()){
            throw new ClienteNaoEncontradoException("Cliente não encontrado para o ID informado.");
        }

        return compraRepositorioDominio.listarPorCliente(id);
    }
}
