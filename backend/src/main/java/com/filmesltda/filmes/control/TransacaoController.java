package com.filmesltda.filmes.control;

import java.util.ArrayList;

import com.filmesltda.filmes.model.Comprovante;
import com.filmesltda.filmes.model.Produto;
import com.filmesltda.filmes.model.Transacao;
import com.filmesltda.filmes.model.Usuario;

public abstract class TransacaoController {

    public final Comprovante adquirirProduto(Transacao t,int tipoPagamento){
        if(!t.VerificarExistencia()){
        if(realizaTransacao(t, tipoPagamento)){
            if(registraTransacao(t)){
                return emitirComprovante(t);
            }
            else{
                return new Comprovante("Não Registrou transação", 0.0, null, null, false);
            }
        }
        }
        return new Comprovante("Não fez o pagamento", 0.0, null, null, false);
    }

    public final ArrayList<Produto> consultarFilmes(Usuario u){
        return retornarFilmes(buscar(u));
    }

    public abstract boolean realizaTransacao(Transacao t,int tipoPagamento);
    public abstract boolean registraTransacao(Transacao t);
    public abstract Comprovante emitirComprovante(Transacao t);

    public abstract ArrayList<Transacao> buscar(Usuario u);
    public abstract ArrayList<Produto> retornarFilmes(ArrayList<Transacao> lista);
}
