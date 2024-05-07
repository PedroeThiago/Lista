package pedro.thiago.lista.adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pedro.thiago.lista.model.MyItem;
import pedro.thiago.lista.R;
import pedro.thiago.lista.activity.MainActivity;

public class MyAdapter extends RecyclerView.Adapter {

    MainActivity mainActivity;
    List<MyItem> itens;
    public MyAdapter(MainActivity mainActivity, List<MyItem> itens) {
        //criando uma classe que herda de RecycleView.Adapter dentro de adapter.
        this.mainActivity = mainActivity;
        //instância para classe MainActivity.
        this.itens = itens;
        //lista de objetos do tipo MyItem.

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Criando uma classe java MyViewHolder que herda de RecycleView.ViewHolder dentro do package adapter.
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(v);
        // Esse inflador será usado para ler o arquivo xml de layout do item e então criar os elementos de interface.
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //Dentro da classe MyAdapter, implemente o método onBindViewHolder:
        MyItem myItem = itens.get(position);
        View v = holder.itemView;

        ImageView imvfoto = v.findViewById(R.id.imvPhoto);
        imvfoto.setImageURI(myItem.photo);

        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);

        TextView tvdesc = v.findViewById(R.id.etdesc);
        tvdesc.setText(myItem.description);
    }
    //o método onBindViewHolder tem como objeto preencher a UI com os
    //dados de um item

    @Override
    public int getItemCount(){
        return itens.size();
    }
    // implementando o metodo gatItemCount.
}
