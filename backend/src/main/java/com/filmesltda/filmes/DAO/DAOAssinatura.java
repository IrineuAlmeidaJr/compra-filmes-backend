package com.filmesltda.filmes.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.filmesltda.filmes.model.Assinatura;
import com.filmesltda.filmes.model.SingletonConexao;

public class DAOAssinatura {
    public boolean salvar(Assinatura a) {
        String sql = "insert into assinatura (ass_nome, ass_tipo, ass_valor,ass_status) values ('$1','$2','$3','$4')";
        sql = sql.replace("$1", a.getNome());
        sql = sql.replace("$2", a.getTipo());
        sql = sql.replace("$3", ""+a.getValor());
        sql = sql.replace("$4", ""+a.isStatus());
        SingletonConexao con = SingletonConexao.getConexao();
        boolean flag = con.manipular(sql);
        return flag;
    }

    public boolean alterar(Assinatura u) {
        String sql = "update assinatura set ass_nome = '$1', ass_tipo = '$2', ass_valor = '$3',ass_status = '$4' where ass_id = "
                + u.getId();
        sql = sql.replace("$1", u.getNome());
        sql = sql.replace("$2", u.getTipo());
        sql = sql.replace("$3",""+ u.getValor());
        sql = sql.replace("$4", ""+u.isStatus());
        SingletonConexao con = SingletonConexao.getConexao();
        boolean flag = con.manipular(sql);
        return flag;
    }

    public ArrayList<Assinatura> buscarTodos(String filtro) {
        ArrayList<Assinatura> Lista = new ArrayList<>();
        String sql = "select * from assinatura";
        if (!filtro.isEmpty()) {
            sql = "select * from assinatura where upper(ass_nome) LIKE " + "'%" + filtro.toUpperCase() + "%'";
        }
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                Lista.add(
                        new Assinatura(rs.getInt("ass_id"), rs.getString("ass_nome"), rs.getString("ass_tipo"),
                        rs.getDouble("ass_valor"), rs.getBoolean("ass_status")));
        } catch (Exception e) {
            System.out.println(e);
        }
        return Lista;
    } 

    public ArrayList<Assinatura> buscarAtivos(String filtro) {
        ArrayList<Assinatura> Lista = new ArrayList<>();
        String sql = "select * from assinatura where ass_status = "+true;
        if (!filtro.isEmpty()) {
            sql = "select * from assinatura where upper(ass_nome) LIKE " + "'%" + filtro.toUpperCase() + "%'"+" and ass_status = "+true;
        }
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                Lista.add(
                        new Assinatura(rs.getInt("ass_id"), rs.getString("ass_nome"), rs.getString("ass_tipo"),
                        rs.getDouble("ass_valor"), rs.getBoolean("ass_status")));
        } catch (Exception e) {
            System.out.println(e);
        }
        return Lista;
    } 

    public ArrayList<Assinatura> buscarInativos(String filtro) {
        ArrayList<Assinatura> Lista = new ArrayList<>();
        String sql = "select * from assinatura where ass_status = "+false;
        if (!filtro.isEmpty()) {
            sql = "select * from assinatura where upper(ass_nome) LIKE " + "'%" + filtro.toUpperCase() + "%'"+" and ass_status = "+false;
        }
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                Lista.add(
                        new Assinatura(rs.getInt("ass_id"), rs.getString("ass_nome"), rs.getString("ass_tipo"),
                        rs.getDouble("ass_valor"), rs.getBoolean("ass_status")));
        } catch (Exception e) {
            System.out.println(e);
        }
        return Lista;
    } 



    public Assinatura buscarUm(int id){
        Assinatura novo = null;
        String sql = "select * from assinatura where ass_id = " + id;
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);

        try {
            if (rs.next())
                novo = new Assinatura(rs.getInt("ass_id"), rs.getString("ass_nome"), rs.getString("ass_tipo"),
                rs.getDouble("ass_valor"), rs.getBoolean("ass_status"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return novo;
    }

    public boolean apagar(int id) {
        Assinatura u = buscarUm(id);
        u.setStatus(false);
        boolean flag;
        flag = alterar(u);
        return flag;
    }

    public boolean ativar(int id) {
        Assinatura u = buscarUm(id);
        u.setStatus(true);
        boolean flag;
        flag = alterar(u);
        return flag;
    }
}
