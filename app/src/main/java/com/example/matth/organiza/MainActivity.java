package com.example.matth.organiza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.matth.organiza.dao.ListaComprasDAO;
import com.example.matth.organiza.dao.ProdutoDAO;
import com.example.matth.organiza.modelo.ListaCompras;
import com.example.matth.organiza.modelo.Produto;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listas = (ListView) findViewById(R.id.lista_listas);

        listas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListaCompras lista = (ListaCompras) listas.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, ListaComprasActivity.class);
                intent.putExtra("lista", lista);
                startActivity(intent);
            }
        });


        Button novaLista = (Button) findViewById(R.id.nova_lista);

        novaLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLista = new Intent(MainActivity.this, ListaComprasFormularioActivity.class);
                startActivity(intentLista);
            }
        });

        registerForContextMenu(listas);

    }

    private void setaLista(){
        ListaComprasDAO dao = new ListaComprasDAO(this);
        List<ListaCompras> listaListas = dao.buscaListas();
        dao.close();

        listas = (ListView) findViewById(R.id.lista_listas);
        ArrayAdapter<ListaCompras> adapter = new ArrayAdapter<ListaCompras>(this, android.R.layout.simple_list_item_1, listaListas);
        listas.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem excluir = menu.add("Excluir lista");

        excluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                ListaCompras l = (ListaCompras) listas.getItemAtPosition(info.position);
                ListaComprasDAO dao = new ListaComprasDAO(MainActivity.this);
                dao.deletaLista(l);
                dao.close();
                setaLista();
                Toast.makeText(MainActivity.this, "Produto deletado", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setaLista();
    }
}
