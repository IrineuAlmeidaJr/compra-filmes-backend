package com.filmesltda.filmes.model;

public interface PagamentoStrategy {
    public boolean pagarAssinatura(Assinatura assinatura, Usuario usuario);
    public boolean pagarCompra(Transacao compra, int formaPagamento);
    public boolean pagarAlugar(Transacao alugar, int formaPagamento);
}
