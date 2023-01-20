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
import com.example.urunkontrol.manager.ManagerChoiceActivity;
import com.example.urunkontrol.manager.ProductAddActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RvAdapterProduct extends RecyclerView.Adapter<RvAdapterProduct.CardViewObjectHolder>{
    private Context mContext;// Contexte ihtiyacımız olacka bşatan tanımladık
    private List<Product> productList;//zaten gelecek veri

    public RvAdapterProduct(Context mContext, List<Product> productList) {
        this.mContext = mContext;
        this.productList = productList ;
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
        Product product  = productList.get(position);
        holder.textViewCard.setText(product.getProductName());
        holder.imageViewMore.setOnClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(mContext,view);
            popupMenu.getMenuInflater().inflate(R.menu.del_edit_menu,popupMenu.getMenu());
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()){
                    case R.id.action_edit:
                        Intent intentEdit = new Intent(mContext, ProductAddActivity.class);
                        intentEdit.putExtra("router",2);
                        intentEdit.putExtra("product_id",product.getProductId());
                        intentEdit.putExtra("qr_or_barcode",product.getBarcodeOrQr());

                        mContext.startActivity(intentEdit);
                        return true;
                    case R.id.action_delete:
                        AlertDialog.Builder ad;
                        ad =new AlertDialog.Builder(mContext);
                        ad.setTitle("Ürünü Sil");
                        ad.setMessage(product.getProductName()+" ürünü silinsin mi ?");//Veri burada
                        ad.setPositiveButton("Evet",(dialogInterface, i) -> {
                            ProductDaoInterface productDif= ApiUtils.getProductInterface();
                            productDif.deleteProduct(product.getProductId()).enqueue(new Callback<ProductResponse>() {
                                @Override
                                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {

                                }

                                @Override
                                public void onFailure(Call<ProductResponse> call, Throwable t) {

                                }
                            });
                            dialogInterface.dismiss();
                        });
                        ad.setNegativeButton("Hayır",(dialogInterface, i) -> {
                            dialogInterface.dismiss();
                        }).show();

                        return true;
                    default:return false;

                }
            });
        });



    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


}
