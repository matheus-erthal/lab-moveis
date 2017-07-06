package com.example.matth.organiza.modelo;

import java.io.Serializable;

/**
 * Created by Matth on 26/06/2017.
 */

public class Produto implements Serializable{

    private long id;
    private String nome;
    private String unidade;
    private double quantidade;

    public Long getLista_id() {
        return lista_id;
    }

    public void setLista_id(Long lista_id) {
        this.lista_id = lista_id;
    }

    private Long lista_id;

    public int getBooleano() {
        return booleano;
    }

    public void setBooleano(int booleano) {
        this.booleano = booleano;
    }

    private int booleano;

    @Override
    public String toString() {
        return getNome() + " - " + getUnidade() + " - " + getQuantidade();
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
