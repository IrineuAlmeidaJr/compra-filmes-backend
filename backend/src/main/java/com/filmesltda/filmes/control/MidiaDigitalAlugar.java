package com.filmesltda.filmes.control;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;


import com.filmesltda.filmes.model.Comprovante;
import com.filmesltda.filmes.model.Produto;
import com.filmesltda.filmes.model.Transacao;
import com.filmesltda.filmes.model.Usuario;

public class MidiaDigitalAlugar extends TransacaoController {

    @Override
    public boolean realizaTransacao(Transacao t, int tipoPagamento) {
        t.setDataexp(LocalDate.now().plusDays(30));
        t.setData(LocalDate.now());
        return t.salvar(tipoPagamento, 2);
    }

    @Override
    public boolean registraTransacao(Transacao t) {
        Produto p = t.getProduto();
        return p.incrementarAluguel();
    }

    @Override
    public Comprovante emitirComprovante(Transacao t) {
        Usuario u = t.getUsuario();
        return new Comprovante(u.getNome(), 0, LocalDateTime.now(), "Aluguel", true);
    }


    @Override
    public ArrayList<Produto> retornarFilmes(ArrayList<Transacao> lista) {
        ArrayList<Produto>p = new ArrayList<>();
        for(int i = 0;i<lista.size();i++){
            p.add(lista.get(i).getProduto());
        }
        return p;
    }

    @Override
    public ArrayList<Transacao> buscar(Usuario u) {
        Transacao t = new Transacao();
        ArrayList <Transacao> lista = t.buscarTodos(u.getId(), 2);
        return lista;
    }


    
}
