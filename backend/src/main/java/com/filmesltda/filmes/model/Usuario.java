package com.filmesltda.filmes.model;

import java.util.ArrayList;

import com.filmesltda.filmes.DAO.DAOUsuario;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String url;
    private int nivel;
    private boolean status;
   
    
    public Usuario(String nome, String email, String senha, String url, int nivel, boolean status) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.url = url;
        this.nivel = nivel;
        this.status = status;
    }
    public Usuario(int id, String nome, String email, String senha, String url, int nivel, boolean status) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.url = url;
        this.nivel = nivel;
        this.status = status;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Usuario() {
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
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public boolean salvar() {
        DAOUsuario dao = new DAOUsuario();
        if (dao.salvar(this)) {
            return true;
        }
        return false;

    }

    public boolean alterar() {
        DAOUsuario dao = new DAOUsuario();
        if (dao.alterar(this)) {
            return true;
        }
        return false;
    }

    public ArrayList<Usuario> buscarTodos(String filtro) {
        DAOUsuario dao = new DAOUsuario();
        ArrayList<Usuario> lista = dao.buscarTodos(filtro);
        return lista;
    }

    public ArrayList<Usuario> buscarAtivos(String filtro) {
        DAOUsuario dao = new DAOUsuario();
        ArrayList<Usuario> lista = dao.buscarAtivos(filtro);
        return lista;
    }

    public ArrayList<Usuario> buscarInativos(String filtro) {
        DAOUsuario dao = new DAOUsuario();
        ArrayList<Usuario> lista = dao.buscarInativos(filtro);
        return lista;
    }

    public Usuario buscarUm(int id) {
        DAOUsuario dao = new DAOUsuario();
        return dao.buscarUm(id);
    }

    public boolean apagar(int id) {
        DAOUsuario dao = new DAOUsuario();
        return dao.apagar(id);
    }

    public boolean ativar(int id) {
        DAOUsuario dao = new DAOUsuario();
        return dao.ativar(id);
    }

    public Usuario buscarEmail(String email){
        DAOUsuario dao = new DAOUsuario();
        return dao.buscarEmail(email);
    }
}
