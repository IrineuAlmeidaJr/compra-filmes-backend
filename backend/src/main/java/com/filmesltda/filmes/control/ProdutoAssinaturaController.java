package com.filmesltda.filmes.control;

import java.time.LocalDate;
import java.util.ArrayList;

import com.filmesltda.filmes.model.Assinatura;
import com.filmesltda.filmes.model.Erro;
import com.filmesltda.filmes.model.Produto;
import com.filmesltda.filmes.model.ProdutoAssinatura;

public class ProdutoAssinaturaController {
    public ProdutoAssinaturaController() {
    }

    public Erro salvar(ArrayList<ProdutoAssinatura> lista) {
        LocalDate lt = LocalDate.now();
        Assinatura a = lista.get(0).getAssinatura();
        System.out.println("tamanho da lista = "+lista.size());
        for (int i = 0; i < lista.size(); i++) {
            if (!new ProdutoAssinatura(lista.get(i).getProduto(), a, lt).salvar()) {
                return new Erro("Erro no banco de dados", true, 500);
            }
        }
        return new Erro("sucesso", false, 200);

    }

    public Erro excluir(ProdutoAssinatura pa) {
        Assinatura a = pa.getAssinatura();
        Produto p = pa.getProduto();
        if (!pa.excluir(a.getId(), p.getId())) {
            return new Erro("Erro no banco de dados", true, 500);
        }
        return new Erro("sucesso", false, 200);
    }

    public ProdutoAssinatura buscarUm(ProdutoAssinatura pa){
        Assinatura a = pa.getAssinatura();
        Produto p = pa.getProduto();
        return pa.buscarUm(a.getId(), p.getId());
    }

    public ArrayList<ProdutoAssinatura> buscarPorAssinatura(Assinatura a){
        return new ProdutoAssinatura().buscarPorAssinatura(a.getId());
    }
}
