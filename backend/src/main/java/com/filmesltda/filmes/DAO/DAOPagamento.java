package com.filmesltda.filmes.DAO;

import java.time.LocalDate;

import com.filmesltda.filmes.model.Assinatura;
import com.filmesltda.filmes.model.Produto;
import com.filmesltda.filmes.model.SingletonConexao;
import com.filmesltda.filmes.model.Usuario;

public class DAOPagamento {
    public boolean salvarAssinatura(Assinatura assinatura, Usuario usuario,double valor) {
        String sql = "insert into pagamento (pag_data, pag_valor,pag_ass,pag_usu) values ('$1','$2','$3','$4')";
        sql = sql.replace("$1", "" + LocalDate.now());
        sql = sql.replace("$2", "" + valor);
        sql = sql.replace("$3", "" + assinatura.getId());
        sql = sql.replace("$4", "" + usuario.getId());
        SingletonConexao con = SingletonConexao.getConexao();
        boolean flag = con.manipular(sql);
        return flag;
    }

    public boolean salvarProduto(Produto produto, Usuario usuario,double valor) {
        String sql = "insert into pagamento (pag_data, pag_valor,pag_prod,pag_usu) values ('$1','$2','$3','$4')";
        sql = sql.replace("$1", "" + LocalDate.now());
        sql = sql.replace("$2", "" + valor);
        sql = sql.replace("$3", "" + produto.getId());
        sql = sql.replace("$4", "" + usuario.getId());
        SingletonConexao con = SingletonConexao.getConexao();
        boolean flag = con.manipular(sql);
        return flag;
    }
}
