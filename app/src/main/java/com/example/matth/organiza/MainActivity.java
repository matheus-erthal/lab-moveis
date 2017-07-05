package com.example.matth.organiza;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    protected void onResume() {
        super.onResume();
        setaLista();
    }
}
