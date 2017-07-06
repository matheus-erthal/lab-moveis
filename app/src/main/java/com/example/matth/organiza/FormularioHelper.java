package com.example.matth.organiza;

import android.widget.EditText;

import com.example.matth.organiza.modelo.Produto;

/**
 * Created by Matth on 26/06/2017.
 */

public class FormularioHelper {

    private EditText campoNome;
    private EditText campoUnidade;
    private EditText campoQuantidade;
    private Produto produto;

    public FormularioHelper(FormularioActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoUnidade = (EditText) activity.findViewById(R.id.formulario_unidade);
        campoQuantidade = (EditText) activity.findViewById(R.id.formulario_quantidade);
        produto = new Produto();
    }

    public Produto pegaProduto(Long lista_id){
        produto.setNome(campoNome.getText().toString());
        produto.setUnidade(campoUnidade.getText().toString());
        produto.setQuantidade(Double.parseDouble(campoQuantidade.getText().toString()));
        produto.setLista_id(lista_id);
        produto.setBooleano(0);
        return produto;
    }

    public void preencheForm(Produto p){
        campoNome.setText(p.getNome());
        campoQuantidade.setText(String.valueOf(p.getQuantidade()));
        campoUnidade.setText(p.getUnidade());
        this.produto = p;
    }
}
