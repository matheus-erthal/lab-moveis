package com.example.matth.organiza;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.matth.organiza.dao.ProdutoDAO;
import com.example.matth.organiza.modelo.ListaCompras;
import com.example.matth.organiza.modelo.Produto;

import java.util.List;

public class ListaComprasActivity extends AppCompatActivity {

    private ListView listaCompras;
    private ListaCompras listaCompra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compras);

        Intent intentLista = getIntent();
        ListaCompras lista = (ListaCompras)  intentLista.getSerializableExtra("lista");
        listaCompra = lista;

        listaCompras = (ListView) findViewById(R.id.lista_compras);

        listaCompras.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produto p = (Produto) listaCompras.getItemAtPosition(position);
                ProdutoDAO dao = new ProdutoDAO(ListaComprasActivity.this);
                if(p.getBooleano() == 0){
                    listaCompras.getChildAt(position).setBackgroundColor(Color.GREEN);
                    p.setBooleano(1);
                    dao.altera(p, listaCompra.getId());
                }else if(p.getBooleano() == 1){
                    listaCompras.getChildAt(position).setBackgroundColor(Color.TRANSPARENT);
                    p.setBooleano(0);
                    dao.altera(p, listaCompra.getId());
                }
                dao.close();
            }
        });


        Button novoProduto = (Button) findViewById(R.id.novo_produto);
        novoProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(ListaComprasActivity.this, FormularioActivity.class);
                intent.putExtra("lista", listaCompra);
                startActivity(intent);
            }
        });

        registerForContextMenu(listaCompras);

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
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    private void setaLista() {
        ProdutoDAO dao = new ProdutoDAO(this);
        List<Produto> listaProdutos = dao.buscaProdutos(listaCompra.getId());
        dao.close();

        listaCompras = (ListView) findViewById(R.id.lista_compras);
        ArrayAdapter<Produto> adapter = new ArrayAdapter<Produto>(this, android.R.layout.simple_list_item_1, listaProdutos);
        listaCompras.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setaLista();
    }

    @Override
    public void onCreateContextMenu(final ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem excluir = menu.add("Excluir produto");
        MenuItem editar = menu.add("Editar produto");

        excluir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Produto p = (Produto) listaCompras.getItemAtPosition(info.position);
                ProdutoDAO dao = new ProdutoDAO(ListaComprasActivity.this);
                dao.deletaProduto(p);
                dao.close();
                setaLista();


                Toast.makeText(ListaComprasActivity.this, "Produto deletado", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Produto p = (Produto) listaCompras.getItemAtPosition(info.position);
                Intent intentVaiProForm = new Intent(ListaComprasActivity.this, FormularioActivity.class);
                intentVaiProForm.putExtra("produto", p);
                startActivity(intentVaiProForm);
                Toast.makeText(ListaComprasActivity.this, "Fon", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

    }
}
