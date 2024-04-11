package pedro.thiago.lista.activity;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pedro.thiago.lista.R;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST =1;

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
    }
}