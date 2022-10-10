package com.filmesltda.filmes.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.filmesltda.filmes.model.Produto;
import com.filmesltda.filmes.model.SingletonConexao;

public class DAOProduto {
    public boolean salvar(Produto p) {
        String sql = "insert into produto (prod_titulo, prod_ano, prod_desc,prod_autor, prod_tipo, prod_valor, prod_responsavel,prod_status,prod_url,prod_foto) values ('$1','$2','$3','$4','$5','$6','$7','$8','$9','$A')";
        sql = sql.replace("$1", p.getTitulo());
        sql = sql.replace("$2", "" + p.getAnoLancamento()); 
        sql = sql.replace("$3", p.getDescricao());
        sql = sql.replace("$4", p.getAutor());
        sql = sql.replace("$5", p.getTipo());
        sql = sql.replace("$6", "" + p.getValor());
        sql = sql.replace("$7", p.getResponsavel());
        sql = sql.replace("$8", "" + p.isStatus());
        sql = sql.replace("$9", p.getUrl());
        sql = sql.replace("$A", p.getFoto());
        SingletonConexao con = SingletonConexao.getConexao();
        boolean flag = con.manipular(sql);
        return flag;
    }

    public boolean alterar(Produto p) {
        String sql = "update produto set prod_titulo = '$1', prod_ano = '$2', prod_desc = '$3',prod_autor = '$4', prod_tipo = '$5', prod_valor = '$6', prod_responsavel = '$7',prod_status = '$8' ,prod_url = '$9',prod_foto = '$A' where prod_id = "
                + p.getId();
        sql = sql.replace("$1", p.getTitulo());
        sql = sql.replace("$2", "" + p.getAnoLancamento());
        sql = sql.replace("$3", p.getDescricao());
        sql = sql.replace("$4", p.getAutor());
        sql = sql.replace("$5", p.getTipo());
        sql = sql.replace("$6", "" + p.getValor());
        sql = sql.replace("$7", p.getResponsavel());
        sql = sql.replace("$8", "" + p.isStatus());
        sql = sql.replace("$9", p.getUrl());
        sql = sql.replace("$A", p.getFoto());
        SingletonConexao con = SingletonConexao.getConexao();
        boolean flag = con.manipular(sql);
        return flag;
    }

    public boolean incrementarVenda(Produto p){
        String sql = "update produto set prod_vendidos = '$1' where prod_id = "+p.getId();
        p.setVendidos(p.getVendidos()+1);
        sql = sql.replace("$1", ""+p.getVendidos());
        SingletonConexao con = SingletonConexao.getConexao();
        boolean flag = con.manipular(sql);
        return flag;
    }

    public boolean incrementarAluguel(Produto p){
        String sql = "update produto set prod_alugados = '$1' where prod_id = "+p.getId();
        sql = sql.replace("$1", ""+p.getAlugados()+1);
        SingletonConexao con = SingletonConexao.getConexao();
        boolean flag = con.manipular(sql);
        return flag;
    }

    public ArrayList<Produto> buscarTodos(String filtro) {
        ArrayList<Produto> Lista = new ArrayList<>();
        String sql = "select * from produto";
        if (!filtro.isEmpty()) {
            sql = "select * from produto where upper(prod_titulo) LIKE " + "'%" + filtro.toUpperCase() + "%'";
        }
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                Lista.add(
                    new Produto(rs.getInt("prod_id"), rs.getString("prod_titulo"), rs.getInt("prod_ano"),
                    rs.getString("prod_desc"), rs.getString("prod_autor"), rs.getString("prod_tipo"),
                    rs.getDouble("prod_valor"), rs.getBoolean("prod_status"),
                    rs.getString("prod_responsavel"),rs.getString("prod_url"),rs.getString("prod_foto"),rs.getInt("prod_vendidos"),rs.getInt("prod_alugados")));
        } catch (Exception e) {
            System.out.println(e);
        }
        return Lista;
    }

    public ArrayList<Produto> buscarMaisVendidos() {
        ArrayList<Produto> Lista = new ArrayList<>();
        String sql = "select * from produto order by prod_vendidos desc limit 5";
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                Lista.add(
                        new Produto(rs.getInt("prod_id"), rs.getString("prod_titulo"), rs.getInt("prod_ano"),
                                rs.getString("prod_desc"), rs.getString("prod_autor"), rs.getString("prod_tipo"),
                                rs.getDouble("prod_valor"), rs.getBoolean("prod_status"),
                                rs.getString("prod_responsavel"),rs.getString("prod_url"),rs.getString("prod_foto"),rs.getInt("prod_vendidos"),rs.getInt("prod_alugados")));
        } catch (Exception e) {
            System.out.println(e);
        }
        return Lista;
    }

    public ArrayList<Produto> buscarMaisAlugados() {
        ArrayList<Produto> Lista = new ArrayList<>();
        String sql = "select * from produto order by prod_alugados desc limit 5";
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                Lista.add(
                        new Produto(rs.getInt("prod_id"), rs.getString("prod_titulo"), rs.getInt("prod_ano"),
                                rs.getString("prod_desc"), rs.getString("prod_autor"), rs.getString("prod_tipo"),
                                rs.getDouble("prod_valor"), rs.getBoolean("prod_status"),
                                rs.getString("prod_responsavel"),rs.getString("prod_url"),rs.getString("prod_foto"),rs.getInt("prod_vendidos"),rs.getInt("prod_alugados")));
        } catch (Exception e) {
            System.out.println(e);
        }
        return Lista;
    }


    public ArrayList<Produto> buscarAtivos(String filtro) {
        ArrayList<Produto> Lista = new ArrayList<>();
        String sql = "select * from produto where prod_status = "+true;
        if (!filtro.isEmpty()) {
            sql = "select * from produto where upper(prod_titulo) LIKE " + "'%" + filtro.toUpperCase() + "%'"+" and prod_status = "+true;
        }
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                Lista.add(
                    new Produto(rs.getInt("prod_id"), rs.getString("prod_titulo"), rs.getInt("prod_ano"),
                                rs.getString("prod_desc"), rs.getString("prod_autor"), rs.getString("prod_tipo"),
                                rs.getDouble("prod_valor"), rs.getBoolean("prod_status"),
                                rs.getString("prod_responsavel"),rs.getString("prod_url"),rs.getString("prod_foto"),rs.getInt("prod_vendidos"),rs.getInt("prod_alugados")));
        } catch (Exception e) {
            System.out.println(e);
        }
        return Lista;
    }

    public ArrayList<Produto> buscarInativos(String filtro) {
        ArrayList<Produto> Lista = new ArrayList<>();
        String sql = "select * from produto where prod_status = "+false;
        if (!filtro.isEmpty()) {
            sql = "select * from produto where upper(prod_titulo) LIKE " + "'%" + filtro.toUpperCase() + "%'"+" and prod_status = "+false;
        }
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
                Lista.add(
                    new Produto(rs.getInt("prod_id"), rs.getString("prod_titulo"), rs.getInt("prod_ano"),
                                rs.getString("prod_desc"), rs.getString("prod_autor"), rs.getString("prod_tipo"),
                                rs.getDouble("prod_valor"), rs.getBoolean("prod_status"),
                                rs.getString("prod_responsavel"),rs.getString("prod_url"),rs.getString("prod_foto"),rs.getInt("prod_vendidos"),rs.getInt("prod_alugados")));
        } catch (Exception e) {
            System.out.println(e);
        }
        return Lista;
    }

    public Produto buscarUm(int id) {
        Produto novo = null;
        String sql = "select * from produto where prod_id = " + id;
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);
        try {
            if (rs.next())
                novo =new Produto(rs.getInt("prod_id"), rs.getString("prod_titulo"), rs.getInt("prod_ano"),
                rs.getString("prod_desc"), rs.getString("prod_autor"), rs.getString("prod_tipo"),
                rs.getDouble("prod_valor"), rs.getBoolean("prod_status"),
                rs.getString("prod_responsavel"),rs.getString("prod_url"),rs.getString("prod_foto"),rs.getInt("prod_vendidos"),rs.getInt("prod_alugados"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return novo;
    }

    public boolean apagar(int id) {
        Produto p = buscarUm(id);
        p.setStatus(false);
        boolean flag;
        flag = alterar(p);
        return flag;
    }

    public boolean ativar(int id) {
        Produto p = buscarUm(id);
        p.setStatus(true);
        boolean flag;
        flag = alterar(p);
        return flag;
    }
}
