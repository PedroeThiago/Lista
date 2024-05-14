package pedro.thiago.lista.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import pedro.thiago.lista.model.MyItem;

    //Guardando itens de MyItem em um ViewModel
public class MainItemActivityViewModel extends ViewModel{
    List<MyItem> itens = new ArrayList<>();
    public List<MyItem> getItens(){
        return itens;
    }
}
