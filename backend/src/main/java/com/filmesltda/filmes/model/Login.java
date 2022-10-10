package com.filmesltda.filmes.model;

public class Login {
    private boolean status;
    private int nivel;
    public Login(boolean b, int nivel) {
        this.status = b;
        this.nivel = nivel;
    }
    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public int getNivel() {
        return nivel;
    }
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
