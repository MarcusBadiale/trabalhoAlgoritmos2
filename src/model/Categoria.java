/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author 631820107
 */
public class Categoria {
    
    private int id;
    private String nome;

    @Override
    public String toString() {
        return nome; //To change body of generated methods, choose Tools | Templates.
    }

//MARK: - Getters
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

//MARK: - Setters
    
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
