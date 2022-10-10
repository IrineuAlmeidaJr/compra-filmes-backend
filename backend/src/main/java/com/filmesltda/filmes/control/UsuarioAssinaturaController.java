package com.filmesltda.filmes.control;

import java.util.ArrayList;

import com.filmesltda.filmes.model.Assinatura;
import com.filmesltda.filmes.model.Erro;
import com.filmesltda.filmes.model.Usuario;
import com.filmesltda.filmes.model.UsuarioAssinatura;

public class UsuarioAssinaturaController {
    public UsuarioAssinaturaController() {
    }

    public Erro salvar(ArrayList<UsuarioAssinatura> lista,int tipoPagamento) {
        Usuario u = lista.get(0).getUsuario();
        for (int i = 0; i < lista.size(); i++) {
            if (!new UsuarioAssinatura(u, lista.get(i).getAssinatura(), lista.get(i).getDataExpiracao()).salvar(tipoPagamento)) {
                return new Erro("Erro no banco de dados", true, 500);
            }
        }
        return new Erro("sucesso", false, 200);

    }

    public Erro excluir(UsuarioAssinatura ua) {
        Assinatura a = ua.getAssinatura();
        Usuario p = ua.getUsuario();
        if (!ua.excluir(a.getId(), p.getId())) {
            return new Erro("Erro no banco de dados", true, 500);
        }
        return new Erro("sucesso", false, 200);
    }

    public UsuarioAssinatura buscarUm(UsuarioAssinatura pa){
        Assinatura a = pa.getAssinatura();
        Usuario p = pa.getUsuario();
        return pa.buscarUm(a.getId(), p.getId());
    }

    public ArrayList<UsuarioAssinatura> buscarPorAssinatura(Assinatura a){
        return new UsuarioAssinatura().buscarPorAssinatura(a.getId());
    }

    public ArrayList<UsuarioAssinatura> buscarPorUsurio(Usuario u){
        return new UsuarioAssinatura().buscarPorUsuario(u.getId());
    }
}
