package com.example.urunkontrol.classes;

import android.content.Context;
import android.hardware.lights.LightState;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urunkontrol.R;

import java.util.List;

public class RvAdapterProductMovementMan extends RecyclerView.Adapter<RvAdapterProductMovementMan.CardViewObjectHolder> {
    private Context mContext;
    private List<ProductMovement> productMovementList;

    public RvAdapterProductMovementMan(Context mContext,List<ProductMovement> productMovementList){
        this.mContext = mContext;
        this.productMovementList = productMovementList ;
    }

    @NonNull
    @Override
    public CardViewObjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_design_movement,parent,false);
        return new CardViewObjectHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewObjectHolder holder, int position) {
        ProductMovement productMovement  = productMovementList.get(position);
        holder.textViewDate.setText(productMovement.getDate());
        holder.textViewState.setText(productMovement.getMovementState());
        holder.textViewPiece.setText("Adet:"+productMovement.getPiece());
        holder.textViewEmployeeNam.setText(productMovement.getUser().getName());
        holder.textViewProductNamMan.setText(productMovement.getProduct().getProductName());

    }

    @Override
    public int getItemCount() {
        return productMovementList.size();
    }

    public class CardViewObjectHolder extends RecyclerView.ViewHolder{
        public TextView textViewDate,textViewState,textViewEmployeeNam,textViewPiece,textViewProductNamMan;
        public CardView cardViewMovement;

        public CardViewObjectHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewPiece = itemView.findViewById(R.id.textViewPiece);
            textViewState = itemView.findViewById(R.id.textViewState);
            textViewEmployeeNam = itemView.findViewById(R.id.textViewEmployeeNam);
            cardViewMovement = itemView.findViewById(R.id.cardViewMovement);
            textViewProductNamMan = itemView.findViewById(R.id.textViewProductNamMan);
        }
    }
}
