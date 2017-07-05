package com.example.matth.organiza.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.matth.organiza.modelo.ListaCompras;
import com.example.matth.organiza.modelo.Produto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matth on 27/06/2017.
 */

public class ListaComprasDAO extends SQLiteOpenHelper {

    public ListaComprasDAO(Context context) {
        super(context, "Listas", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE lista(id INTEGER PRIMARY KEY, nome TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS lista";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insere(ListaCompras lista) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("nome", lista.getNome());
        db.insert("lista", null, dados);
    }

    public List<ListaCompras> buscaListas() {
        String sql = "SELECT * FROM lista";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        List<ListaCompras> listas = new ArrayList<ListaCompras>();
        while(c.moveToNext()){
            ListaCompras l = new ListaCompras();
            l.setId(c.getLong(c.getColumnIndex("id")));
            l.setNome(c.getString(c.getColumnIndex("nome")));
            listas.add(l);
        }
        c.close();
        return listas;
    }







}
