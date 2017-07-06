package com.example.matth.organiza;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.matth.organiza.modelo.Produto;

import java.util.List;

/**
 * Created by Matth on 03/07/2017.
 */

public class AdapterListaCompras extends BaseAdapter {

    private final List<Produto> produtos;
    private final Activity act;

    public AdapterListaCompras(Activity act, List<Produto> produtos){
        this.produtos = produtos;
        this.act = act;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return produtos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = act.getLayoutInflater().inflate(R.layout.lista_personalizada, parent, false);
        Produto p = produtos.get(position);

        TextView nome = (TextView) v.findViewById(R.id.lista_personalizada_nome);
        TextView unidade = (TextView) v.findViewById(R.id.lista_personalizada_unidade);
        TextView quantidade = (TextView) v.findViewById(R.id.lista_personalizada_quantidade);

        nome.setText(p.getNome());
        unidade.setText(p.getUnidade());
        quantidade.setText(String.valueOf(p.getQuantidade()));


        if(p.getBooleano() == 1){
            v.setBackgroundColor(Color.GREEN);
        }else if(p.getBooleano() == 0){
            v.setBackgroundColor(Color.TRANSPARENT);
        }

        return v;
    }
}
