package com.filmesltda.filmes.DAO;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import com.filmesltda.filmes.model.Assinatura;
import com.filmesltda.filmes.model.Produto;
import com.filmesltda.filmes.model.ProdutoAssinatura;
import com.filmesltda.filmes.model.SingletonConexao;

public class DAOProdutoAssinatura {
    public boolean salvar(Assinatura a, Produto p) {
        String sql = "insert into produtoassinatura (pa_ass_id, pa_prod_id, pa_data) values ('$1','$2','$3')";
        LocalDate data = LocalDate.now();
        sql = sql.replace("$1", ""+a.getId());
        sql = sql.replace("$2", ""+p.getId());
        sql = sql.replace("$3", ""+data);
        SingletonConexao con = SingletonConexao.getConexao();
        boolean flag = con.manipular(sql);
        return flag;
    }

    public boolean excluir(int prod_id,int ass_id){
        String sql = "delete from produtoassinatura where pa_ass_id = "+ass_id+" and pa_prod_id = "+prod_id;
        SingletonConexao con = SingletonConexao.getConexao();
        boolean flag = con.manipular(sql);
        return flag;
    }

    public ProdutoAssinatura buscarUm(int prod_id,int ass_id){
        ProdutoAssinatura pa = null;
        String sql = "select * from produtoassinatura where pa_ass_id = "+ass_id+" and pa_prod_id = "+prod_id;
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);
        try {
            if (rs.next())
                pa = new ProdutoAssinatura(new DAOProduto().buscarUm(rs.getInt("pa_prod_id")),new DAOAssinatura().buscarUm(rs.getInt("pa_ass_id")),rs.getDate("pa_data").toLocalDate());
        } catch (Exception e) {
            System.out.println(e);
        }
        return pa;
    }

    public ArrayList<ProdutoAssinatura> buscarPorAssinatura(int ass_id){
        ArrayList <ProdutoAssinatura> pa = new ArrayList<>();
        String sql = "select * from produtoassinatura where pa_ass_id = "+ass_id;
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
               pa.add(new ProdutoAssinatura(new DAOProduto().buscarUm(rs.getInt("pa_prod_id")),new DAOAssinatura().buscarUm(rs.getInt("pa_ass_id")),rs.getDate("pa_data").toLocalDate()));
        } catch (Exception e) {
            System.out.println(e);
        }
        return pa;
    }
}
