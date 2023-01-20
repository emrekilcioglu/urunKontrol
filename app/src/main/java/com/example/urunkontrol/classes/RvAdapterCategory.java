package com.example.urunkontrol.classes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urunkontrol.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RvAdapterCategory extends RecyclerView.Adapter<RvAdapterCategory.CardViewObjectHolder>{
    private Context mContext;// Contexte ihtiyacımız olacka bşatan tanımladık
    private List<Category> categories;//zaten gelecek veri



    public RvAdapterCategory(Context mContext, List<Category> categories) {
        this.mContext = mContext;
        this.categories = categories ;
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
        Category category  = categories.get(position);
        holder.textViewCard.setText(category.getCategoryName());
        holder.imageViewMore.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(mContext,view);
            popupMenu.getMenuInflater().inflate(R.menu.del_edit_menu,popupMenu.getMenu());
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()){
                    case R.id.action_edit:
                        LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        View view1 = li.inflate(R.layout.allert_design,null);
                        EditText editTextInsert = view1.findViewById(R.id.editTextInsert);
                        AlertDialog.Builder ad2;
                        editTextInsert.setHint("Kategori İsmi Giriniz");
                        editTextInsert.setText(category.getCategoryName());
                        ad2 =new AlertDialog.Builder(mContext);
                        ad2.setTitle("Kategori Güncelle");
                        ad2.setView(view1);
                        ad2.setPositiveButton("Güncelle",(dialogInterface, i) -> {
                            CategoryDaoInterface categoryDif = ApiUtils.getCategoryInterface();
                            String categoryName = editTextInsert.getText().toString();
                            categoryDif.updateCategory(category.getCategoryId(),categoryName).enqueue(new Callback<CRUDResponse>() {
                                @Override
                                public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {

                                }

                                @Override
                                public void onFailure(Call<CRUDResponse> call, Throwable t) {

                                }
                            });
                            dialogInterface.dismiss();
                        });
                        ad2.setNegativeButton("İptal",(dialogInterface, i) -> {
                            dialogInterface.dismiss();
                        }).show();





                        return true;
                    case R.id.action_delete:
                        AlertDialog.Builder ad;
                        ad =new AlertDialog.Builder(mContext);
                        ad.setTitle("Kategori Sil");
                        ad.setMessage(category.getCategoryName()+" kategorisi silinsin mi ?");//Veri burada
                        ad.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                CategoryDaoInterface categoryDif = ApiUtils.getCategoryInterface();
                                categoryDif.deleteCategory(category.getCategoryId()).enqueue(new Callback<CRUDResponse>() {
                                    @Override
                                    public void onResponse(Call<CRUDResponse> call, Response<CRUDResponse> response) {
                                        dialogInterface.dismiss();
                                    }

                                    @Override
                                    public void onFailure(Call<CRUDResponse> call, Throwable t) {

                                    }
                                });

                            }
                        });
                        ad.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
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
        return categories.size();
    }


}
