package com.filmesltda.filmes.model;

import java.time.LocalDateTime;

public class Comprovante {
    private String nomeUsuario;
    private double valorTotal;
    private LocalDateTime data;
    private String tipo;
    private boolean status;
    
    
    public Comprovante(String nomeUsuario, double valorTotal, LocalDateTime data, String tipo, boolean status) {
        this.nomeUsuario = nomeUsuario;
        this.valorTotal = valorTotal;
        this.data = data;
        this.tipo = tipo;
        this.status = status;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getNomeUsuario() {
        return nomeUsuario;
    }
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public LocalDateTime getData() {
        return data;
    }
    public void setData(LocalDateTime data) {
        this.data = data;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
