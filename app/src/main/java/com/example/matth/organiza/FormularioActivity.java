package com.example.matth.organiza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.matth.organiza.dao.ProdutoDAO;
import com.example.matth.organiza.modelo.ListaCompras;
import com.example.matth.organiza.modelo.Produto;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;
    private ListaCompras listaCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Intent intentLista = getIntent();
        ListaCompras lista = (ListaCompras)  intentLista.getSerializableExtra("lista");
        listaCompra = lista;
        helper = new FormularioHelper(this);
        Toast.makeText(FormularioActivity.this, "Teste: "+lista.getNome(), Toast.LENGTH_SHORT).show();
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
                Produto produto = helper.pegaProduto(listaCompra.getId());
                ProdutoDAO dao = new ProdutoDAO(this);
                dao.insere(produto);
                dao.close();
                Toast.makeText(FormularioActivity.this, "Item "+produto.getNome()+" criado!", Toast.LENGTH_SHORT).show();


                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
