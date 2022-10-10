package com.filmesltda.filmes.model;

import java.util.ArrayList;

import com.filmesltda.filmes.DAO.DAOAssinatura;

public class Assinatura {
    private int id;
    private String nome;
    private String tipo;
    private double valor;
    private boolean status;


    public Assinatura(String nome, String tipo, double valor, boolean status) {
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.status = status;
    }

    public Assinatura(int id, String nome, String tipo, double valor, boolean status) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Assinatura() {
    }

    public boolean salvar() {
        DAOAssinatura dao = new DAOAssinatura();
        if (dao.salvar(this)) {
            return true;
        }
        return false;

    }

    public boolean alterar() {
        DAOAssinatura dao = new DAOAssinatura();
        if (dao.alterar(this)) {
            return true;
        }
        return false;
    }

    public ArrayList<Assinatura> buscarTodos(String filtro) {
        DAOAssinatura dao = new DAOAssinatura();
        ArrayList<Assinatura> lista = dao.buscarTodos(filtro);
        return lista;
    }

    public ArrayList<Assinatura> buscarAtivos(String filtro) {
        DAOAssinatura dao = new DAOAssinatura();
        ArrayList<Assinatura> lista = dao.buscarAtivos(filtro);
        return lista;
    }

    public ArrayList<Assinatura> buscarInativos(String filtro) {
        DAOAssinatura dao = new DAOAssinatura();
        ArrayList<Assinatura> lista = dao.buscarInativos(filtro);
        return lista;
    }

    public Assinatura buscarUm(int id) {
        DAOAssinatura dao = new DAOAssinatura();
        return dao.buscarUm(id);
    }

    public boolean apagar(int id) {
        DAOAssinatura dao = new DAOAssinatura();
        return dao.apagar(id);
    }

    public boolean ativar(int id) {
        DAOAssinatura dao = new DAOAssinatura();
        return dao.ativar(id);
    }

}
