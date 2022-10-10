package com.filmesltda.filmes.control;

import com.filmesltda.filmes.model.Login;
import com.filmesltda.filmes.model.Usuario;

public class LoginController {
    public LoginController(){}
    public Login validarLogin(String email, String senha){
        Usuario u = new Usuario().buscarEmail(email);
        if(u != null){
            if(senha.equals(u.getSenha())){
                if(u.isStatus()){
                    return new Login(true,u.getNivel());
                }
            }else{
                return new Login(false, 0);
            }
        }
        return new Login(false, 0);
    }
}
