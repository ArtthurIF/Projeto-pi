package controllers;

import java.util.List;

import models.Viagem;

import play.mvc.Controller;

public class Agencia extends Controller {
    
    public static void form(){
        render();
    }
    
    public static void salvar(Viagem v) {
        String mensagem = "Cadastro realizado com sucesso!";
        if(v.id != null){
            mensagem = "Viagem editada com sucesso!";
        }
        v.save();
        flash.success(mensagem);
        listar(null);
    }
    
    public static void listar(String termo) {
        List<Viagem> viagens = null;
        if(termo == null) {
            viagens = Viagem.findAll();
        } else {
            viagens = Viagem.find("lower(cliente) like ?1",
                    "%" + termo.toLowerCase() + "%").fetch();
        }
        render(viagens, termo);
    }
    
    public static void remover(long id) {
        Viagem v = Viagem.findById(id);
        v.delete();
        flash.success("Viagem removida com sucesso!");
        listar(null);
    } 
    
    public static void editar(Long id) {
        Viagem v = Viagem.findById(id);
        renderTemplate("Agencia/form.html", v);
    }

}