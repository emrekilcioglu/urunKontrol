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

public class RvAdapterBrand extends RecyclerView.Adapter<RvAdapterBrand.CardViewObjectHolder>{
    private Context mContext;// Contexte ihtiyacımız olacka bşatan tanımladık
    private List<Brand> brandList;//zaten gelecek veri

    public RvAdapterBrand(Context mContext, List<Brand> brandList) {
        this.mContext = mContext;
        this.brandList = brandList ;
    }
    public class CardViewObjectHolder extends RecyclerView.ViewHolder{
        //Bu sınıf görsel elemanarla ilgilidir
        public TextView textViewCard;
        public CardView cardView;
        public ImageView imageViewMore;

        public CardViewObjectHolder(@NonNull View itemView) {
            super(itemView);
            textViewCard = itemView.findViewById(R.id.textViewCard);
            cardView = itemView.findViewById(R.id.cardView);
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
        Brand brand  = brandList.get(position);
        holder.textViewCard.setText(brand.getBrandName());


    }

    @Override
    public int getItemCount() {
        return brandList.size();
    }


}
