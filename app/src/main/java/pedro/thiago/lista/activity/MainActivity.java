package pedro.thiago.lista.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import pedro.thiago.lista.adapter.MyAdapter;
import pedro.thiago.lista.model.MainItemActivityViewModel;
import pedro.thiago.lista.model.MyItem;
import pedro.thiago.lista.R;
import pedro.thiago.lista.util.Util;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST = 1;
    List<MyItem> itens = new ArrayList<>();
    MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtendo botão
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);
        //Setando ouvidor de clicks
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent explicito para navegar para NewItenActivity
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);
                //startActivityForResult informa que a activity destino retornará dados
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });

        RecyclerView rvItens = findViewById(R.id.rvItens);
        MainItemActivityViewModel vm = new ViewModelProvider(this).get(MainItemActivityViewModel.class);
        List<MyItem> itens = vm.getItens();


        myAdapter = new MyAdapter(this,itens);
        //cria uma nova instância de MyAdapter

        rvItens.setAdapter(myAdapter);
        rvItens.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration((rvItens.getContext()), (DividerItemDecoration.VERTICAL));
        rvItens.addItemDecoration(dividerItemDecoration);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        //herda atributos de onActivityResult

        if (requestCode == NEW_ITEM_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                MyItem myItem = new MyItem();
                myItem.title = data.getStringExtra("title");
                myItem.description = data.getStringExtra("description");
                Uri selectedPhotoUri = data.getData();

                try {
                    //Guardando a imagem dentro de um Bitmap, utilizando função presente em Util.java
                    Bitmap photo = Util.getBitmap(MainActivity.this, selectedPhotoUri, 100, 100);
                    //Guardando Bitmap dentro de um objeto do tipo MyItem
                    myItem.photo = photo;
                }
                catch (FileNotFoundException e){
                    //Excessão disparada caso o arquivo de imagem não seja encontrado
                    e.printStackTrace();
                }

                itens.add(myItem);
                myAdapter.notifyItemInserted(itens.size() - 1);
            //coleta os resultados setados em NewItemActivity e adiciona o novo item à lista.

                //obtendo ViewModel
                MainItemActivityViewModel vm = new ViewModelProvider(this).get(MainItemActivityViewModel.class);
                //obtendo a lista de itens em ViewModel
                List<MyItem> itens = vm.getItens();
                //guardando itens em nova lista
                itens.add(myItem);
                myAdapter.notifyItemInserted(itens.size()-1);
            }
        }
    }
}
