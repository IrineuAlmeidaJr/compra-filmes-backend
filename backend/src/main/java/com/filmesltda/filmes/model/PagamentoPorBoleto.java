package com.filmesltda.filmes.model;

import com.filmesltda.filmes.DAO.DAOPagamento;

public class PagamentoPorBoleto implements PagamentoStrategy {


    @Override
    public boolean pagarAssinatura(Assinatura assinatura, Usuario usuario) {
        DAOPagamento dao = new DAOPagamento();
        double por = assinatura.getValor()*0.10;
        return dao.salvarAssinatura(assinatura, usuario, assinatura.getValor()-por);
    }

    @Override
    public boolean pagarCompra(Transacao compra, int formaPagamento) {
        if(formaPagamento == 1) { // Da new porque é Boleto
            DAOPagamento dao = new DAOPagamento();
            Produto p = compra.getProduto();
            double por = p.getValor()*0.10;
            return dao.salvarProduto(compra.getProduto(), compra.getUsuario(),p.getValor()-por);
        }
        else {
            return new PagamentoPorCartao().pagarCompra(compra, formaPagamento);
        }

    }

    @Override
    public boolean pagarAlugar(Transacao alugar, int formaPagamento) {
        if(formaPagamento == 1) {
            DAOPagamento dao = new DAOPagamento();
            Produto p = alugar.getProduto();
            double por = p.getValor()*0.10;
            return dao.salvarProduto(alugar.getProduto(), alugar.getUsuario(),por);
        } else {
            return new PagamentoPorCartao().pagarAlugar(alugar, formaPagamento);
        }

    }
    
}
