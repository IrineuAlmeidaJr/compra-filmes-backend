package com.filmesltda.filmes.model;

import java.time.LocalDate;
import java.util.ArrayList;

import com.filmesltda.filmes.DAO.DAOTransacao;

public class Transacao {
    private int id;
    private LocalDate data;
    private Produto produto;
    private Usuario usuario;
    private LocalDate dataexp;
    private int tipo;
    
    
    public Transacao(int id, LocalDate data, Produto produto, Usuario usuario, int tipo) {
        this.id = id;
        this.data = data;
        this.produto = produto;
        this.usuario = usuario;
        this.tipo = tipo;
    }

    public Transacao(LocalDate data, Produto produto, Usuario usuario, LocalDate dataexp, int tipo) {
        this.data = data;
        this.produto = produto;
        this.usuario = usuario;
        this.dataexp = dataexp;
        this.tipo = tipo;
    }

    public Transacao(int id, LocalDate data, Produto produto, Usuario usuario, LocalDate dataexp, int tipo) {
        this.id = id;
        this.data = data;
        this.produto = produto;
        this.usuario = usuario;
        this.dataexp = dataexp;
        this.tipo = tipo;
    }

    public LocalDate getDataexp() {
        return dataexp;
    }

    public void setDataexp(LocalDate dataexp) {
        this.dataexp = dataexp;
    }
    public int getTipo() {
        return tipo;
    }
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    public Transacao() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public Produto getProduto() {
        return produto;
    }
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean salvar(int tipoPagamento,int tipoTransacao) {
        System.out.println("tipo de transação = "+tipoTransacao);
        this.setTipo(tipoTransacao);
            if(tipoTransacao == 1)
                new PagamentoPorBoleto().pagarCompra(this, tipoPagamento);
            else
                new PagamentoPorBoleto().pagarAlugar(this, tipoPagamento);


        DAOTransacao dao = new DAOTransacao();
        if (dao.salvar(this)) {
            return true;
        }
        return false;
    }


    public ArrayList<Transacao> buscarTodos(int id,int tipo) {
        DAOTransacao dao = new DAOTransacao();
        ArrayList<Transacao> lista = dao.buscarTodos(id,tipo);
        System.out.println("tamanho da lista - "+lista.size());
        return lista;
    }

    public boolean VerificarExistencia(){
        Produto p = getProduto();
        Usuario u = getUsuario();
        return new DAOTransacao().VerificarExistencia(u.getId(), p.getId());
    }

    
    
}
