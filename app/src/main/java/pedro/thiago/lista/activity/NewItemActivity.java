package pedro.thiago.lista.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import pedro.thiago.lista.R;

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;
    //Uri serve para guardar os endereços das fotos, e não as fotos em sí
    Uri photoSelected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        //obtendo o botão imgCI
        ImageButton imgCI = findViewById(R.id.imbCI);

        //definindo ouvidor clicks
        imgCI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //executando a abertura da galeria de fotos
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                //apenas documentos do tipo image/*
                photoPickerIntent.setType("image/*");
                //executando intent
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }

        });
        //obtendo botão
        Button btnAddItem = findViewById(R.id.btnAddItem);
        //setando ouvidor de clicks no botão
        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            //informando que é necessario inserir os dados
            public void onClick(View v) {
                if (photoSelected == null) {
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                if (title.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                if (description.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                }

                //criando intent vazia e colocando dados nela
                Intent i = new Intent();
                i.setData(photoSelected);
                i.putExtra("title", title);
                i.putExtra("description", description);
                //setando resultados para serem retornados
                setResult(Activity.RESULT_OK, i);
                //fechando activity e retornando para anterior
                finish();
            }
        });
    }
    @Override
    //requestCode - indica a qual chamada a resposta se refere
    //resultCode - indica se a activity retornou com sucesso
    //data - intent que contém os dados a serem retornados
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        //verifica se o id da requestCode é PHOTO_PICKER_REQUEST
        if (requestCode == PHOTO_PICKER_REQUEST){
            //verificando se resultCode é um codigo de sucesso
            if (resultCode == Activity.RESULT_OK){
                photoSelected = data.getData();
                ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
                imvfotoPreview.setImageURI(photoSelected);
            }
        }
    }
}
