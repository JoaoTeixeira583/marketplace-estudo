package br.com.estudo.marketplaceestudo.compra.application;

import br.com.estudo.marketplaceestudo.compra.Compra;
import br.com.estudo.marketplaceestudo.compra.CompraId;
import br.com.estudo.marketplaceestudo.compra.CompraRepositorioDominio;
import br.com.estudo.marketplaceestudo.evento.application.DevolverIngressoUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CancelarCompraUseCase {

    private final CompraRepositorioDominio compraRepositorioDominio;
    private final DevolverIngressoUseCase devolverIngressoUseCase;

    public CancelarCompraUseCase(CompraRepositorioDominio compraRepositorioDominio,
                                 DevolverIngressoUseCase devolverIngressoUseCase
                                 ){
        this.compraRepositorioDominio = compraRepositorioDominio;
        this.devolverIngressoUseCase = devolverIngressoUseCase;
    }

    @Transactional(transactionManager = "transactionManager")
    public Compra executar(CompraId id){
        var compraEncontrada = compraRepositorioDominio.buscarPorId(id);
        if(compraEncontrada.isEmpty()){
            throw new CompraNaoEncontradaException("Compra não encontrada para o ID informado.");
        }

        Compra compra = compraEncontrada.get();
        compra.cancelar();
        devolverIngressoUseCase.executar(compra.getEventoId());
        compraRepositorioDominio.salvar(compra);
        return compra;
    }
}
