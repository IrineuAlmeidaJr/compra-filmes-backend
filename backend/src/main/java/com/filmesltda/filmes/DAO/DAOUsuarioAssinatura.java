package com.filmesltda.filmes.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.filmesltda.filmes.model.Assinatura;
import com.filmesltda.filmes.model.SingletonConexao;
import com.filmesltda.filmes.model.Usuario;
import com.filmesltda.filmes.model.UsuarioAssinatura;

public class DAOUsuarioAssinatura {
    public boolean salvar(UsuarioAssinatura a) {
        Assinatura as = a.getAssinatura();
        Usuario u = a.getUsuario();
        String sql = "insert into usuarioassinatura (ua_ass_id, ua_usu_id, ua_data) values ('$1','$2','$3')";
        sql = sql.replace("$1", ""+as.getId());
        sql = sql.replace("$2", ""+u.getId());
        sql = sql.replace("$3", ""+a.getDataExpiracao());
        SingletonConexao con = SingletonConexao.getConexao();
        boolean flag = con.manipular(sql);
        return flag;
    }

    public boolean excluir(int usu_id,int ass_id){
        String sql = "delete from usuarioassinatura where ua_ass_id = "+ass_id+" and ua_usu_id = "+usu_id;
        SingletonConexao con = SingletonConexao.getConexao();
        boolean flag = con.manipular(sql);
        return flag;
    }

    public UsuarioAssinatura buscarUm(int usu_id,int ass_id){
        UsuarioAssinatura pa = null;
        String sql = "select * from usuarioassinatura where pa_ass_id = "+ass_id+" and pa_prod_id = "+usu_id;
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);
        try {
            if (rs.next())
                pa = new UsuarioAssinatura(new DAOUsuario().buscarUm(rs.getInt("pa_prod_id")),new DAOAssinatura().buscarUm(rs.getInt("pa_ass_id")),rs.getDate("pa_data").toLocalDate());
        } catch (Exception e) {
            System.out.println(e);
        }
        return pa;
    }

    public ArrayList<UsuarioAssinatura> buscarPorAssinatura(int ass_id){
        ArrayList <UsuarioAssinatura> pa = new ArrayList<>();
        String sql = "select * from usuarioassinatura where ua_ass_id = "+ass_id;
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
               pa.add(new UsuarioAssinatura(new DAOUsuario().buscarUm(rs.getInt("ua_usu_id")),new DAOAssinatura().buscarUm(rs.getInt("ua_ass_id")),rs.getDate("ua_data").toLocalDate()));
        } catch (Exception e) {
            System.out.println(e);
        }
        return pa;
    }

    public ArrayList<UsuarioAssinatura> buscarPorUsuario(int usu_id){
        ArrayList <UsuarioAssinatura> pa = new ArrayList<>();
        String sql = "select * from usuarioassinatura where ua_usu_id = "+usu_id;
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
               pa.add(new UsuarioAssinatura(new DAOUsuario().buscarUm(rs.getInt("ua_usu_id")),new DAOAssinatura().buscarUm(rs.getInt("ua_ass_id")),rs.getDate("ua_data").toLocalDate()));
        } catch (Exception e) {
            System.out.println(e);
        }
        return pa;
    }
}
