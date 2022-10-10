package com.filmesltda.filmes.control;

import java.util.ArrayList;



import com.filmesltda.filmes.model.Erro;
import com.filmesltda.filmes.model.Produto;

public class ProdutoController {
    public ProdutoController(){}
    public Erro salvar(Produto p) {
        if (!p.getAutor().isEmpty()) {
            if (!p.getDescricao().isEmpty()) {
                if (!p.getTipo().isEmpty()) {
                    if (!p.getTitulo().isEmpty()) {
                        if (!p.getResponsavel().isEmpty()) {
                           
                            if (!p.salvar()) {
                                return new Erro("Erro interno no banco de dados!!!", true,500);
                            }
                        }

                    } else {
                        return new Erro("Preencha o título do produto", true,400);
                    }

                } else {
                    return new Erro("Preencha o tipo do produto", true,400);
                }
            } else {
                return new Erro("Preencha a descrição do produto", true,400);
            }
        } else {
            return new Erro("Preencha o nome do autor", true,400);
        }
        return new Erro("Sucesso", false,200);

    }

    public Erro atualizar(Produto p){
        if (!p.getAutor().isEmpty()) {
            if (!p.getDescricao().isEmpty()) {
                if (!p.getTipo().isEmpty()) {
                    if (!p.getTitulo().isEmpty()) {
                        if (!p.getResponsavel().isEmpty()) {
                            if (!p.alterar()) {
                                return new Erro("Erro interno no banco de dados!!!", true,500);
                            }
                        }

                    } else {
                        return new Erro("Preencha o título do produto", true,400);
                    }

                } else {
                    return new Erro("Preencha o tipo do produto", true,400);
                }
            } else {
                return new Erro("Preencha a descrição do produto", true,400);
            }
        } else {
            return new Erro("Preencha o nome do autor", true,400);
        }
        return new Erro("Sucesso", false,200);
    }

    public ArrayList<Produto> buscarTodos(String filtro){
        return new Produto().buscarTodos(filtro);
    }

    public ArrayList<Produto> buscarAtivos(String filtro){
        return new Produto().buscarAtivos(filtro);
    }

    public ArrayList<Produto> buscarInativos(String filtro){
        return new Produto().buscarInativos(filtro);
    }

    public ArrayList<Produto> buscarMaisVendidos(){
        return new Produto().buscarMaisVendidos();
    }

    public ArrayList<Produto> buscarMaisAlugados(){
        return new Produto().buscarMaisAlugados();
    }

    public Produto buscarum(int id){
        if(id>0){
            return new Produto().buscarUm(id);
        }
        else{
            return new Produto();
        }
    }

    public Erro desativar(int id){
        if(id>0){
            try{
                new Produto().apagar(id);
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
                new Produto().ativar(id);
            }catch(Exception e){
                return new Erro("Não foi possível desativar", true, 500);
            }
            return new Erro("Desativado com sucesso", true, 200);
        }
        return new Erro("Id inválido", true, 400);
    }
}
