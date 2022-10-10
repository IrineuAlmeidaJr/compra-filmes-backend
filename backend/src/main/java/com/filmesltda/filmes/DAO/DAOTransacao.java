package com.filmesltda.filmes.DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.filmesltda.filmes.model.Transacao;
import com.filmesltda.filmes.model.Produto;
import com.filmesltda.filmes.model.SingletonConexao;
import com.filmesltda.filmes.model.Usuario;

public class DAOTransacao {
    public boolean salvar(Transacao a) {
        String sql;
        if (a.getTipo() != 1) {
            sql = "insert into transacao (trans_data, trans_prodid, trans_usuid,trans_tipo,trans_dataexp) values ('$1','$2','$3','$4','$5')";
        } else {
            sql = "insert into transacao (trans_data, trans_prodid, trans_usuid,trans_tipo) values ('$1','$2','$3','$4')";
        }
        sql = sql.replace("$1", "" + a.getData());
        Produto prod = a.getProduto();
        sql = sql.replace("$2", "" + prod.getId());
        Usuario usu = a.getUsuario();
        sql = sql.replace("$3", "" + usu.getId());
        sql = sql.replace("$4", "" + a.getTipo());
        sql = sql.replace("$5", "" + a.getDataexp());
        System.out.println("data - " + a.getData());
        System.out.println("produto - " + prod.getId());
        System.out.println("usuario - " + usu.getId());
        System.out.println("tipo - " + a.getTipo());
        System.out.println("dataexp - " + a.getDataexp());
        System.out.println(sql);
        SingletonConexao con = SingletonConexao.getConexao();
        boolean flag = con.manipular(sql);
        return flag;
    }

    public boolean alterar(Transacao a) {
        String sql = "update transacao set trans_data = '$1', trans_prodid = '$2', trans_usuid = '$3',trans_tipo = '$4', trans_dataexp = '$5' where trans_id = "
                + a.getId();
        sql = sql.replace("$1", "" + a.getData());
        Produto prod = a.getProduto();
        sql = sql.replace("$2", "" + prod.getId());
        Usuario usu = a.getUsuario();
        sql = sql.replace("$3", "" + usu.getId());
        sql = sql.replace("$4", "" + a.getTipo());
        sql = sql.replace("$5", "" + a.getDataexp());
        SingletonConexao con = SingletonConexao.getConexao();
        boolean flag = con.manipular(sql);
        return flag;
    }

    public ArrayList<Transacao> buscarTodos(int id, int tipo) {
        ArrayList<Transacao> Lista = new ArrayList<>();
        String sql;
        if (tipo == 1) {
            sql = "select * from transacao where trans_usuid = " + id + " and trans_tipo = " + tipo;
        } else {
            sql = "select * from transacao where trans_usuid = " + id + " and trans_tipo = " + tipo
                    + " and trans_dataexp>current_date";
        }
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);
        try {
            while (rs.next())
            if(tipo == 1)
                Lista.add(
                        new Transacao(rs.getInt("trans_id"), rs.getDate("trans_data").toLocalDate(),
                                new DAOProduto().buscarUm(rs.getInt("trans_prodid")),
                                new DAOUsuario().buscarUm(rs.getInt("trans_usuid")),
                                 rs.getInt("trans_tipo")));
            else{
                Lista.add(new Transacao(rs.getInt("trans_id"), rs.getDate("trans_data").toLocalDate(),
                                new DAOProduto().buscarUm(rs.getInt("trans_prodid")),
                                new DAOUsuario().buscarUm(rs.getInt("trans_usuid")),
                                 rs.getDate("trans_dataexp").toLocalDate(),rs.getInt("trans_tipo")));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return Lista;
    }

    public Transacao buscarUm(int id) {
        Transacao novo = null;
        String sql = "select * from transacao where trans_id = " + id;
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);

        try {
            if (rs.next())
                novo = new Transacao(rs.getInt("trans_id"), rs.getDate("trans_data").toLocalDate(),
                        new DAOProduto().buscarUm(rs.getInt("trans_prodid")),
                        new DAOUsuario().buscarUm(rs.getInt("trans_usuid")), rs.getDate("trans_dataexp").toLocalDate(),
                        rs.getInt("trans_tipo"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return novo;
    }

    public boolean VerificarExistencia(int usuid, int prodid) {
        String sql = "select * from transacao where trans_prodid = " + prodid + " and trans_usuid = " + usuid;
        SingletonConexao con = SingletonConexao.getConexao();
        ResultSet rs = con.consultar(sql);

        int numLinhas = 0;
        try{
        while (rs.next()) {
            numLinhas++;
        }
        }catch(Exception e){

        }
        System.out.println("Há " + numLinhas + " linhas na query.");
        if (numLinhas>0) {
            return true;
        }
        return false;
    }

}
