package com.filmesltda.filmes.model;

import com.filmesltda.filmes.DAO.DAOPagamento;

public class PagamentoPorPix implements PagamentoStrategy {

    @Override
    public boolean pagarAssinatura(Assinatura assinatura, Usuario usuario) {
        DAOPagamento dao = new DAOPagamento();
        double por = assinatura.getValor()*0.07;
        return dao.salvarAssinatura(assinatura, usuario, assinatura.getValor()-por);
    }

    @Override
    public boolean pagarCompra(Transacao compra, int formaPagamento) {
        DAOPagamento dao = new DAOPagamento();
        Produto p = compra.getProduto();
        double por = p.getValor()*0.07;
        return dao.salvarProduto(compra.getProduto(), compra.getUsuario(),p.getValor()-por);
    }

    @Override
    public boolean pagarAlugar(Transacao alugar, int formaPagamento) {
        DAOPagamento dao = new DAOPagamento();
        Produto p = alugar.getProduto();
        double por = p.getValor()*0.11;
        return dao.salvarProduto(alugar.getProduto(), alugar.getUsuario(),por);
    }

   
    
}
