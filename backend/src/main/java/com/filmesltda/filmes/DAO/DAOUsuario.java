package com.filmesltda.filmes.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.filmesltda.filmes.model.SingletonConexao;
import com.filmesltda.filmes.model.Usuario;

public class DAOUsuario {
    public boolean salvar(Usuario u) {
        String sql = "insert into usuario (usu_nome, usu_email, usu_senha,usu_nivel,usu_status,usu_url) values ('$1','$2','$3','$4','$5','$6')";
        sql = sql.replace("$1", u.getNome());
        sql = sql.replace("$2", u.getEmail());
        sql = sql.replace("$3", u.getSenha());
        sql = sql.replace("$4", ""+u.getNivel());
        sql = sql.replace("$5", ""+u.isStatus());
        sql = sql.replace("$6", u.getUrl());
        SingletonConexao con = SingletonConexao.getConexao();
        boolean flag = con.manipular(sql);
        return flag;
    }

    public boolean alterar(Usuario u) {
        String sql = "update usuario set usu_nome = '$1', usu_email = '$2', usu_senha = '$3',usu_nivel = '$4', usu_status = '$5',usu_url = '$6' where usu_id = "
                + u.getId();
        sql = sql.replace("$1", u.getNome());
        sql = sql.replace("$2", u.getEmail());
        sql = sql.replace("$3", u.getSenha());
        sql = sql.replace("$4", ""+u.getNivel());
        sql = sql.replace("$5", ""+u.isStatus());
        sql = sql.replace("$6", u.getUrl());
        SingletonConexao con = SingletonConexao.getConexao();
        boolean flag = con.manipular(sql);
        return flag;
    }

    public ArrayList<Usuario> buscarTodos(String filtro) {
        ArrayList<Usuario> Lista = new ArrayList<>();
        String sql = "select * from usuario";
        if (!filtro.isEmpty()) {
            sql = "select * from usuario where upper(usu_nome) LIKE " + "'%" + filtro.toUpperCase() + "%'";
        }
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                Lista.add(
                        new Usuario(rs.getInt("usu_id"), rs.getString("usu_nome"), rs.getString("usu_email"),
                        rs.getString("usu_senha"),rs.getString("usu_url"), rs.getInt("usu_nivel"),rs.getBoolean("usu_status")));
        } catch (Exception e) {
            System.out.println(e);
        }
        return Lista;
    } 

    public ArrayList<Usuario> buscarAtivos(String filtro) {
        ArrayList<Usuario> Lista = new ArrayList<>();
        String sql = "select * from usuario where usu_status = "+true;
        System.out.println("FILTRO -> " + filtro);
        if (!filtro.isEmpty()) {
            sql = "select * from usuario where usu_email = " + "'" + filtro + "'"+" and usu_status = "+true;
        }
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                Lista.add(
                        new Usuario(rs.getInt("usu_id"), rs.getString("usu_nome"), rs.getString("usu_email"),
                        rs.getString("usu_senha"),rs.getString("usu_url"), rs.getInt("usu_nivel"),rs.getBoolean("usu_status")));
        } catch (Exception e) {
            System.out.println(e);
        }
        return Lista;
    } 

    public ArrayList<Usuario> buscarInativos(String filtro) {
        ArrayList<Usuario> Lista = new ArrayList<>();
        String sql = "select * from usuario where usu_status = "+false;
        if (!filtro.isEmpty()) {
            sql = "select * from usuario where upper(usu_nome) LIKE " + "'%" + filtro.toUpperCase() + "%'"+" and usu_status = "+false;
        }
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                Lista.add(
                        new Usuario(rs.getInt("usu_id"), rs.getString("usu_nome"), rs.getString("usu_email"),
                        rs.getString("usu_senha"),rs.getString("usu_url"), rs.getInt("usu_nivel"),rs.getBoolean("usu_status")));
        } catch (Exception e) {
            System.out.println(e);
        }
        return Lista;
    } 

    public Usuario buscarUm(int id){
        Usuario novo = null;
        String sql = "select * from usuario where usu_id = " + id;
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);

        try {
            if (rs.next())
                novo = new Usuario(rs.getInt("usu_id"), rs.getString("usu_nome"), rs.getString("usu_email"),
                rs.getString("usu_senha"),rs.getString("usu_url"), rs.getInt("usu_nivel"),rs.getBoolean("usu_status"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return novo;
    }

    public Usuario buscarEmail(String email){
        Usuario novo = null;
        String sql = "select * from usuario where usu_email LIKE " + "'" + email + "'";
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);

        try {
            if (rs.next())
                novo = new Usuario(rs.getInt("usu_id"), rs.getString("usu_nome"), rs.getString("usu_email"),
                rs.getString("usu_senha"),rs.getString("usu_url"), rs.getInt("usu_nivel"),rs.getBoolean("usu_status"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return novo;
    }

    public boolean apagar(int id) {
        Usuario u = buscarUm(id);
        u.setStatus(false);
        boolean flag;
        flag = alterar(u);
        return flag;
    }

    public boolean ativar(int id) {
        Usuario u = buscarUm(id);
        u.setStatus(true);
        boolean flag;
        flag = alterar(u);
        return flag;
    }
}
