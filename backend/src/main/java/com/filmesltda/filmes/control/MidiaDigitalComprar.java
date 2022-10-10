package com.filmesltda.filmes.control;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.filmesltda.filmes.model.Comprovante;
import com.filmesltda.filmes.model.Produto;
import com.filmesltda.filmes.model.Transacao;
import com.filmesltda.filmes.model.Usuario;

public class MidiaDigitalComprar extends TransacaoController {

    @Override
    public boolean realizaTransacao(Transacao t, int tipoPagamento) {
        t.setData(LocalDate.now());
        System.out.println("fez a transação");
        return t.salvar(tipoPagamento, 1);
    }

    @Override
    public boolean registraTransacao(Transacao t) {
        System.out.println(" registrou a transação");
        Produto p = t.getProduto();
        return p.incrementarVenda();
    }

    @Override
    public Comprovante emitirComprovante(Transacao t) {
        Usuario u = t.getUsuario();
        return new Comprovante(u.getNome(), 0, LocalDateTime.now(), "Compra", true);
    }

    @Override
    public ArrayList<Transacao> buscar(Usuario u) {
        Transacao t = new Transacao();
        ArrayList <Transacao> lista = t.buscarTodos(u.getId(), 1);
        return lista;
    }

    @Override
    public ArrayList<Produto> retornarFilmes(ArrayList<Transacao> lista) {
        ArrayList<Produto>p = new ArrayList<>();
        System.out.println(lista.size());
        for(int i = 0;i<lista.size();i++){
            System.out.print(lista.get(i).getProduto());
            p.add(lista.get(i).getProduto());
        }
        return p;
    }

   
    
}
