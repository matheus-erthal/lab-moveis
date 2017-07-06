package com.example.matth.organiza.modelo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Matth on 26/06/2017.
 */

public class ListaCompras implements Serializable {

    private String nome;
    private long id;

    @Override
    public String toString() {
        return this.id + " - " + this.nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
