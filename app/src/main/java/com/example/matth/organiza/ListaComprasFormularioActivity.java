package com.example.matth.organiza;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.matth.organiza.dao.ListaComprasDAO;
import com.example.matth.organiza.modelo.ListaCompras;

/**
 * Created by Matth on 27/06/2017.
 */

public class ListaComprasFormularioActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compras_formulario);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                ListaCompras lista = new ListaCompras();
                ListaComprasDAO dao = new ListaComprasDAO(this);
                EditText campoNome = (EditText) findViewById(R.id.formulario_nome);
                lista.setNome(campoNome.getText().toString());
                dao.insere(lista);
                dao.close();
                Toast.makeText(ListaComprasFormularioActivity.this, "Lista "+lista.getNome()+" criada!", Toast.LENGTH_SHORT).show();


                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
