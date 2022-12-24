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

public class RvAdapterTransHistory extends RecyclerView.Adapter<RvAdapterTransHistory.CardViewObjectHolder>{
    private Context mContext;// Contexte ihtiyacımız olacka bşatan tanımladık
    private List<ProductMovement> transHistoryList;//zaten gelecek veri

    public RvAdapterTransHistory(Context mContext, List<ProductMovement> transHistoryList) {
        this.mContext = mContext;
        this.transHistoryList = transHistoryList ;
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
    public RvAdapterTransHistory.CardViewObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
        Bu metodda görsel elemnanımıza construcktor yolu ile tasarımı gönderiyoruz
         */
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_design,parent,false);

        return new RvAdapterTransHistory.CardViewObjectHolder(view);
        // TODO: 13/11/2022 adapterı oluşturmada kaldık tıklanma özelliklerini yap
    }

    @Override
    public void onBindViewHolder(@NonNull RvAdapterTransHistory.CardViewObjectHolder holder, int position) {//item count kadar çalışır
        ProductMovement productMovement  = transHistoryList.get(position);
        holder.textViewCard.setText(productMovement.getProduct().getProductName() + "   " + productMovement.getMovementState());


    }

    @Override
    public int getItemCount() {
        return transHistoryList.size();
    }

}
