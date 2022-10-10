package com.filmesltda.filmes.control;

import java.util.ArrayList;

import com.filmesltda.filmes.model.Erro;
import com.filmesltda.filmes.model.Usuario;

public class UsuarioController {
    public UsuarioController(){}
    public Erro salvar(Usuario usuario){
        usuario.setNivel(0);
        usuario.setStatus(true);
        if(!usuario.getEmail().isEmpty()){
            if(!usuario.getNome().isEmpty()){
                if(usuario.getSenha().length() >= 5){
                    if(!usuario.salvar()){
                        return new Erro("Erro no banco de dados", true, 500);
                    }
                }else{
                    return new Erro("Senha muito curta", true, 400);
                }
            }else{
                return new Erro("Por favor, preencha o nome do usuário", true, 400);
            }
        }else{
            return new Erro("Por favor, preencha o email do usuário", true, 500);
        }
        return new Erro("Sucesso", false, 200);
    }

    public Erro alterar(Usuario usuario){
        if(!usuario.getEmail().isEmpty()){
            if(!usuario.getNome().isEmpty()){
                if(usuario.getSenha().length() >= 5){
                    if(!usuario.alterar()){
                        return new Erro("Erro no banco de dados", true, 500);
                    }
                }else{
                    return new Erro("Senha muito curta", true, 400);
                }
            }else{
                return new Erro("Por favor, preencha o nome do usuário", true, 400);
            }
        }else{
            return new Erro("Por favor, preencha o email do usuário", true, 500);
        }
        return new Erro("Sucesso", false, 200);
    }

    public ArrayList<Usuario> buscarTodos(String filtro){
        return new Usuario().buscarTodos(filtro);
    }

    public ArrayList<Usuario> buscarAtivos(String filtro){
        return new Usuario().buscarAtivos(filtro);
    }

    public ArrayList<Usuario> buscarInativos(String filtro){
        return new Usuario().buscarInativos(filtro);
    }

    public Usuario buscarum(int id){
        if(id>0){
            return new Usuario().buscarUm(id);
        }
        else{
            return new Usuario();
        }
    }

    public Erro desativar(int id){
        if(id>0){
            try{
                new Usuario().apagar(id);
            }catch(Exception e){
                return new Erro("Não foi possível desativar", true, 500);
            }
            return new Erro("Desativado com sucesso", true, 200);
        }
        return new Erro("Id inválido", true, 400);
    }

    public Erro ativar(int id){
        if(id>0){
            try{
                new Usuario().ativar(id);
            }catch(Exception e){
                return new Erro("Não foi possível desativar", true, 500);
            }
            return new Erro("Desativado com sucesso", true, 200);
        }
        return new Erro("Id inválido", true, 400);
    }
}
