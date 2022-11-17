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
        Category category  = categories.get(position);
        holder.textViewCard.setText(category.getCategoryName());


    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


}
