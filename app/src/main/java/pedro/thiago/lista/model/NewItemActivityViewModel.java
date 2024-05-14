package pedro.thiago.lista.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class NewItemActivityViewModel extends ViewModel {

    //Guardando endereço Uri da foto

    Uri selectPhotoLocation = null;

    //Obtendo lista de itens
    public Uri getSelectPhotoLocation(){
        return selectPhotoLocation;
    }

    //Setando endereço Uri dentro de ViewModel
    public void setSelectPhotoLocation(Uri selectPhotoLocation){
        this.selectPhotoLocation = selectPhotoLocation;
    }

}
