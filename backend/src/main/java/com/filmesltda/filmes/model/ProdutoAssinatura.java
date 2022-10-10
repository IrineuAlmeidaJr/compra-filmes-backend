package com.filmesltda.filmes.model;

import java.time.LocalDate;
import java.util.ArrayList;

import com.filmesltda.filmes.DAO.DAOProdutoAssinatura;

public class ProdutoAssinatura {
    private Produto produto;
    private Assinatura assinatura;
    private LocalDate data;

    public ProdutoAssinatura(){}

    public ProdutoAssinatura(Produto produto, Assinatura assinatura, LocalDate data) {
        this.produto = produto;
        this.assinatura = assinatura;
        this.data = data;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public Assinatura getAssinatura() {
        return assinatura;
    }
    public void setAssinatura(Assinatura assinatura) {
        this.assinatura = assinatura;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }

    public boolean salvar(){
        DAOProdutoAssinatura dao = new DAOProdutoAssinatura();
        if (dao.salvar(this.assinatura,this.produto)) {
            return true;
        }
        return false;
    }

    public boolean excluir(int ass_id,int prod_id){
        DAOProdutoAssinatura dao = new DAOProdutoAssinatura();
        if (dao.excluir(prod_id,ass_id)) {
            return true;
        }
        return false;
    }

    public ProdutoAssinatura buscarUm(int ass_id,int prod_id){
        DAOProdutoAssinatura dao = new DAOProdutoAssinatura();
        return dao.buscarUm(prod_id, ass_id);
    }
    public ArrayList<ProdutoAssinatura> buscarPorAssinatura(int ass_id) {
        DAOProdutoAssinatura dao = new DAOProdutoAssinatura();
        ArrayList<ProdutoAssinatura> lista = dao.buscarPorAssinatura(ass_id);
        return lista;
    }

}
