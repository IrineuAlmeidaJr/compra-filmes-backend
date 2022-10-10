package com.filmesltda.filmes.model;

import java.util.ArrayList;

import com.filmesltda.filmes.DAO.DAOProduto;

public class Produto {
    private int id;
    private String titulo;
    private int anoLancamento;
    private String descricao; 
    private String autor;
    private String tipo;
    private Double valor;
    private boolean status;
    private String responsavel;
    private String url;
    private String foto;
    private int vendidos;
    private int alugados;

    

    public Produto(int id, String titulo, int anoLancamento, String descricao, String autor, String tipo, Double valor,
            boolean status, String responsavel, String url, String foto, int vendidos, int alugados) {
        this.id = id;
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.descricao = descricao;
        this.autor = autor;
        this.tipo = tipo;
        this.valor = valor;
        this.status = status;
        this.responsavel = responsavel;
        this.url = url;
        this.foto = foto;
        this.vendidos = vendidos;
        this.alugados = alugados;
    }

    public int getVendidos() {
        return vendidos;
    }

    public void setVendidos(int vendidos) {
        this.vendidos = vendidos;
    }

    public int getAlugados() {
        return alugados;
    }

    public void setAlugados(int alugados) {
        this.alugados = alugados;
    }

    public Produto(String titulo, int anoLancamento, String descricao, String autor, String tipo, Double valor,
            boolean status, String responsavel, String url, String foto) {
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.descricao = descricao;
        this.autor = autor;
        this.tipo = tipo;
        this.valor = valor;
        this.status = status;
        this.responsavel = responsavel;
        this.url = url;
        this.foto = foto;
    }

    public Produto(int id, String titulo, int anoLancamento, String descricao, String autor, String tipo, Double valor,
            boolean status, String responsavel, String url, String foto) {
        this.id = id;
        this.titulo = titulo;
        this.anoLancamento = anoLancamento;
        this.descricao = descricao;
        this.autor = autor;
        this.tipo = tipo;
        this.valor = valor;
        this.status = status;
        this.responsavel = responsavel;
        this.url = url;
        this.foto = foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Produto() {
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public boolean salvar() {
        DAOProduto dao = new DAOProduto();
        if (dao.salvar(this)) {
            return true;
        }
        return false;

    }

    public boolean incrementarVenda() {
        DAOProduto dao = new DAOProduto();
        if (dao.incrementarVenda(this)) {
            return true;
        }
        return false;

    }

    public boolean incrementarAluguel() {
        DAOProduto dao = new DAOProduto();
        if (dao.incrementarAluguel(this)) {
            return true;
        }
        return false;

    }

    public boolean alterar() {
        DAOProduto dao = new DAOProduto();
        if (dao.alterar(this)) {
            return true;
        }
        return false;
    }

    public ArrayList<Produto> buscarTodos(String filtro) {
        DAOProduto dao = new DAOProduto();
        ArrayList<Produto> lista = dao.buscarTodos(filtro);
        return lista;
    }

    public ArrayList<Produto> buscarAtivos(String filtro) {
        DAOProduto dao = new DAOProduto();
        ArrayList<Produto> lista = dao.buscarAtivos(filtro);
        return lista;
    }

    public ArrayList<Produto> buscarInativos(String filtro) {
        DAOProduto dao = new DAOProduto();
        ArrayList<Produto> lista = dao.buscarInativos(filtro);
        return lista;
    }

    public ArrayList<Produto> buscarMaisVendidos() {
        DAOProduto dao = new DAOProduto();
        ArrayList<Produto> lista = dao.buscarMaisVendidos();
        return lista;
    }

    public ArrayList<Produto> buscarMaisAlugados() {
        DAOProduto dao = new DAOProduto();
        ArrayList<Produto> lista = dao.buscarMaisAlugados();
        return lista;
    }


    public Produto buscarUm(int id) {
        DAOProduto dao = new DAOProduto();
        return dao.buscarUm(id);
    }

    public boolean apagar(int id) {
        DAOProduto dao = new DAOProduto();
        return dao.apagar(id);
    }

    public boolean ativar(int id) {
        DAOProduto dao = new DAOProduto();
        return dao.ativar(id);
    }
    

}
