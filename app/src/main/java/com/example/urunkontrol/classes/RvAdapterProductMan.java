package com.example.urunkontrol.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urunkontrol.R;

import java.util.List;

public class RvAdapterProductMan extends RecyclerView.Adapter<RvAdapterProductMan.CardViewObjectHolder>{
    private Context mContext;// Contexte ihtiyacımız olacka bşatan tanımladık
    private List<Product> productList;//zaten gelecek veri

    public RvAdapterProductMan(Context mContext, List<Product> productList) {
        this.mContext = mContext;
        this.productList = productList ;
    }
    public class CardViewObjectHolder extends RecyclerView.ViewHolder{
        //Bu sınıf görsel elemanarla ilgilidir
        public TextView textViewCardPro,textViewStock;
        public ImageView imageViewProMan;

        public CardViewObjectHolder(@NonNull View itemView) {
            super(itemView);
            textViewCardPro = itemView.findViewById(R.id.textViewCardPro);
            imageViewProMan = itemView.findViewById(R.id.imageViewProMan);
            textViewStock = itemView.findViewById(R.id.textViewStock);
        }
    }

    @NonNull
    @Override
    public CardViewObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
        Bu metodda görsel elemnanımıza construcktor yolu ile tasarımı gönderiyoruz
         */
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_design_pro_man,parent,false);

        return new CardViewObjectHolder(view);
        // TODO: 13/11/2022 adapterı oluşturmada kaldık tıklanma özelliklerini yap 
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewObjectHolder holder, int position) {//item count kadar çalışır
        Product product  = productList.get(position);
        holder.textViewCardPro.setText(product.getProductName());
        holder.textViewStock.setText(product.getStockLevel()+"/"+product.getMaxStockLevel());
        if (Integer.parseInt(product.getStockLevel()) == 0){
            holder.imageViewProMan.setImageResource(R.drawable.danger);
        }

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


}
