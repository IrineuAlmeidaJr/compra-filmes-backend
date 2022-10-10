package com.filmesltda.filmes.control;

import java.util.ArrayList;

import com.filmesltda.filmes.model.Assinatura;
import com.filmesltda.filmes.model.Erro;

public class AssinaturaController {

    public Erro salvarAssinatura(Assinatura assinatura){
        if(assinatura.getValor()>0){
            if(!assinatura.getNome().isEmpty()){
                if(!assinatura.getTipo().isEmpty()){
                    if(!assinatura.salvar()){
                        return new Erro("Erro na inserção de dados no banco", true, 500);
                    }
                }else{
                    return new Erro("Preencha o tipo de assinatura", true, 400);
                }
            }else{
                return new Erro("Preencha o nome da assinatura", true, 400);
            }
        }else{
            return new Erro("Valor deve ser maior que 0", true, 400);
        }

        return new Erro("Sucesso", false, 200);
    }

    public Erro atualizarAssinatura(Assinatura assinatura){
        if(assinatura.getValor()>0){
            if(!assinatura.getNome().isEmpty()){
                if(!assinatura.getTipo().isEmpty()){
                    if(!assinatura.alterar()){
                        return new Erro("Erro na inserção de dados no banco", true, 500);
                    }
                }else{
                    return new Erro("Preencha o tipo de assinatura", true, 400);
                }
            }else{
                return new Erro("Preencha o nome da assinatura", true, 400);
            }
        }else{
            return new Erro("Valor deve ser maior que 0", true, 400);
        }

        return new Erro("Sucesso", false, 200);
    }

    public ArrayList<Assinatura> buscarInativos(String filtro){
        return new Assinatura().buscarInativos(filtro);
    }

    public ArrayList<Assinatura> buscarAtivos(String filtro){
        return new Assinatura().buscarAtivos(filtro);
    }

    public ArrayList<Assinatura> buscarTodos(String filtro){
        return new Assinatura().buscarInativos(filtro);
    }

    

    public Assinatura buscarum(int id){
        if(id>0){
            return new Assinatura().buscarUm(id);
        }
        else{
            return new Assinatura();
        }
    }

    public Erro desativar(int id){
        if(id>0){
            try{
                new Assinatura().apagar(id);
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
                new Assinatura().ativar(id);
            }catch(Exception e){
                return new Erro("Não foi possível desativar", true, 500);
            }
            return new Erro("Desativado com sucesso", true, 200);
        }
        return new Erro("Id inválido", true, 400);
    }

}
