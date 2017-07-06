package com.example.matth.organiza.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.matth.organiza.modelo.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matth on 26/06/2017.
 */

public class ProdutoDAO extends SQLiteOpenHelper {

    public ProdutoDAO(Context context) {
        super(context, "Produto", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE produto(id INTEGER PRIMARY KEY, nome TEXT NOT NULL, unidade TEXT NOT NULL," +
                " quantidade REAL, lista_id INTEGER, booleano INTEGER);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS produto";
        db.execSQL(sql);
        onCreate(db);

    }

    public void insere(Produto produto) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("nome", produto.getNome());
        dados.put("unidade", produto.getUnidade());
        dados.put("quantidade", produto.getQuantidade());
        dados.put("lista_id", produto.getLista_id());
        dados.put("booleano", produto.getBooleano());
        db.insert("produto", null, dados);

    }

    public void altera(Produto produto, long id){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("nome", produto.getNome());
        dados.put("unidade", produto.getUnidade());
        dados.put("quantidade", produto.getQuantidade());
        dados.put("lista_id", id);
        dados.put("booleano", produto.getBooleano());
        String[] params = {String.valueOf(produto.getId())};
        db.update("produto", dados, "id = ?", params);

    }

    public List<Produto> buscaProdutos(Long lista_id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM produto WHERE lista_id=?", new String[]{lista_id.toString()});
        List<Produto> produtos = new ArrayList<Produto>();
        while(c.moveToNext()){
            Produto p = new Produto();
            p.setId(c.getLong(c.getColumnIndex("id")));
            p.setNome(c.getString(c.getColumnIndex("nome")));
            p.setUnidade(c.getString(c.getColumnIndex("unidade")));
            p.setQuantidade(c.getDouble(c.getColumnIndex("quantidade")));
            p.setBooleano(c.getInt(c.getColumnIndex("booleano")));
            produtos.add(p);
        }
        c.close();
        return produtos;
    }

    public void deletaProduto(Produto p) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(p.getId())};
        db.delete("produto", "id = ?", params);

    }
}
