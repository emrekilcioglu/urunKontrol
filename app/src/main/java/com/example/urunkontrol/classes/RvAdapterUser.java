package com.example.urunkontrol.classes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urunkontrol.R;
import com.example.urunkontrol.manager.AddUserActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RvAdapterUser extends RecyclerView.Adapter<RvAdapterUser.CardViewObjectHolder>{
    private Context mContext;// Contexte ihtiyacımız olacka bşatan tanımladık
    private List<User> userList;//zaten gelecek veri

    public RvAdapterUser(Context mContext, List<User> userList) {
        this.mContext = mContext;
        this.userList = userList ;
    }
    public class CardViewObjectHolder extends RecyclerView.ViewHolder{
        //Bu sınıf görsel elemanarla ilgilidir
        public TextView textViewCard;
        public CardView cardView;
        public ImageView imageViewMore;

        public CardViewObjectHolder(@NonNull View itemView) {
            super(itemView);
            textViewCard = itemView.findViewById(R.id.textViewCardPro);
            cardView = itemView.findViewById(R.id.cardViewMovement);
            imageViewMore = itemView.findViewById(R.id.imageViewMore);
        }
    }

    @NonNull
    @Override
    public CardViewObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
        Bu metodda görsel elemnanımıza construcktor yolu ile tasarımı gönderiyoruz
         */
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_design,parent,false);

        return new CardViewObjectHolder(view);
        // TODO: 13/11/2022 adapterı oluşturmada kaldık tıklanma özelliklerini yap 
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewObjectHolder holder, int position) {//item count kadar çalışır
        User user  = userList.get(position);
        holder.textViewCard.setText(user.getName());
        holder.imageViewMore.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(mContext, view);
            popupMenu.getMenuInflater().inflate(R.menu.del_edit_menu, popupMenu.getMenu());
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()) {
                    case R.id.action_edit:
                        Intent intentEdit = new Intent(mContext, AddUserActivity.class);
                        intentEdit.putExtra("router", 1);
                        intentEdit.putExtra("user_id", user.getUserId());

                        mContext.startActivity(intentEdit);
                        return true;
                    case R.id.action_delete:
                        AlertDialog.Builder ad;
                        ad =new AlertDialog.Builder(mContext);
                        ad.setTitle("Kullanıcı Sil");
                        ad.setMessage(user.getName()+" kullanıcısı silinsin mi ?");//Veri burada
                        ad.setPositiveButton("Evet",(dialogInterface, i) -> {
                            UserDaoInterface userDif = ApiUtils.getUserInterface();
                            userDif.deleteUser(user.getUserId()).enqueue(new Callback<UserResponse>() {
                                @Override
                                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {

                                }

                                @Override
                                public void onFailure(Call<UserResponse> call, Throwable t) {

                                }
                            });

                        });
                        ad.setNegativeButton("Hayır",(dialogInterface, i) -> {
                            dialogInterface.dismiss();
                        }).show();

                        return true;
                    default:
                        return false;

                }
            });
        });


    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


}
