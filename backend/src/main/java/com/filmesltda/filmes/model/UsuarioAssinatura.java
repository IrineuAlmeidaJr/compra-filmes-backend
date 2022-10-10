package com.filmesltda.filmes.model;

import java.time.LocalDate;
import java.util.ArrayList;

import com.filmesltda.filmes.DAO.DAOUsuarioAssinatura;

public class UsuarioAssinatura {
    private Usuario usuario;
    private Assinatura assinatura;
    private LocalDate dataExpiracao;
    public UsuarioAssinatura(){}
    public UsuarioAssinatura(Usuario usuario, Assinatura assinatura, LocalDate dataExpiracao) {
        this.usuario = usuario;
        this.assinatura = assinatura;
        this.dataExpiracao = dataExpiracao;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Assinatura getAssinatura() {
        return assinatura;
    }
    public void setAssinatura(Assinatura assinatura) {
        this.assinatura = assinatura;
    }
    public LocalDate getDataExpiracao() {
        return dataExpiracao;
    }
    public void setDataExpiracao(LocalDate dataExpiracao) {
        this.dataExpiracao = dataExpiracao;
    }

    public boolean salvar(int tipoPagamento){
        if(tipoPagamento == 1){
            new PagamentoPorBoleto().pagarAssinatura(this.assinatura, this.usuario);
        }else if(tipoPagamento == 2){
            new PagamentoPorCartao().pagarAssinatura(this.assinatura, this.usuario);
        }else{
            new PagamentoPorPix().pagarAssinatura(this.assinatura, this.usuario);
        }
        DAOUsuarioAssinatura dao = new DAOUsuarioAssinatura();
        if (dao.salvar(this)) {
            return true;
        }
        return false;
    }
    public boolean excluir(int ass_id,int usu_id){
        DAOUsuarioAssinatura dao = new DAOUsuarioAssinatura();
        if (dao.excluir(usu_id,ass_id)) {
            return true;
        }
        return false;
    }

    public UsuarioAssinatura buscarUm(int usu_id,int ass_id){
        DAOUsuarioAssinatura dao = new DAOUsuarioAssinatura();
        return dao.buscarUm(usu_id, ass_id);
    }
    public ArrayList<UsuarioAssinatura> buscarPorAssinatura(int ass_id) {
        DAOUsuarioAssinatura dao = new DAOUsuarioAssinatura();
        ArrayList<UsuarioAssinatura> lista = dao.buscarPorAssinatura(ass_id);
        return lista;
    }

    public ArrayList<UsuarioAssinatura> buscarPorUsuario(int usu_id) {
        DAOUsuarioAssinatura dao = new DAOUsuarioAssinatura();
        ArrayList<UsuarioAssinatura> lista = dao.buscarPorUsuario(usu_id);
        return lista;
    }



    
}
