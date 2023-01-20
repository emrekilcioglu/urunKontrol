package com.example.urunkontrol.manager;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.urunkontrol.R;
import com.example.urunkontrol.classes.ApiUtils;
import com.example.urunkontrol.classes.Stock;
import com.example.urunkontrol.classes.StockDaoInterface;
import com.example.urunkontrol.classes.StockResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StockTrackFragment extends Fragment {
    private ProgressBar progressBarStock;
    private TextView textViewStockPercent,textViewDangerInf;
    private ImageView imageViewDanger;
    private Button buttonTransactionHis;
    private StockDaoInterface stockDif;
    private View stockDangerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_stock_track, container, false);
        progressBarStock = rootView.findViewById(R.id.progressBarStock);
        textViewStockPercent = rootView.findViewById(R.id.textViewStockPercent);
        buttonTransactionHis = rootView.findViewById(R.id.buttonTransactionHis);
        imageViewDanger = rootView.findViewById(R.id.imageViewDanger);
        textViewDangerInf = rootView.findViewById(R.id.textViewDangerInf);
        stockDangerView = rootView.findViewById(R.id.stockDangerView);
        setStockInf();

        buttonTransactionHis.setOnClickListener(view -> {
            Intent intentList = new Intent(getContext(),StockTrackListActivity.class);
            intentList.putExtra("router",1);
            startActivity(intentList);
        });



        return rootView;
    }
    private void setStockInf(){
        stockDif = ApiUtils.getStockInterface();
        stockDif.allStock().enqueue(new Callback<StockResponse>() {
            @Override
            public void onResponse(Call<StockResponse> call, Response<StockResponse> response) {
                Stock stockList = response.body().getStock().get(0);
                String generalStock = stockList.getGeneralStock();
                progressBarStock.setProgress(Integer.parseInt(generalStock));
                textViewStockPercent.setText("%"+generalStock);
                //inner join kullanılabilir
                int dangerStock,emptyStock;
                dangerStock =Integer.parseInt(stockList.getDangerStock());
                emptyStock = Integer.parseInt(stockList.getEmptyStock());
                if (dangerStock>0){
                    StringBuilder sbDanger = new StringBuilder();
                    sbDanger.append(dangerStock+" Adet ürünün stok seviyesi tehlikeli seviyede.");
                    if (emptyStock>0){
                        sbDanger.append("\n\n"+emptyStock+"Adet ürünün stoğu kalmamıştır.");
                        imageViewDanger.setImageDrawable(getResources().getDrawable(R.drawable.danger));
                    }else imageViewDanger.setImageDrawable(getResources().getDrawable(R.drawable.warning_icon));

                    textViewDangerInf.setText(sbDanger.toString());
                   stockTrack();


                }
                else if(emptyStock>0){
                    textViewDangerInf.setText(emptyStock+"Adet ürünün stoğu kalmamıştır.");
                    imageViewDanger.setImageDrawable(getResources().getDrawable(R.drawable.danger));
                    stockTrack();



                }
                else {
                    imageViewDanger.setImageDrawable(getResources().getDrawable(R.drawable.good_icon));
                    textViewDangerInf.setText("Stoklar iyi durumda");
                    stockDangerView.setOnClickListener(view -> {
                        Toast.makeText(getContext(), "Ürünler İyi durumda", Toast.LENGTH_SHORT).show();
                    });


                }

            }

            @Override
            public void onFailure(Call<StockResponse> call, Throwable t) {

            }
        });

    }
    private void stockTrack(){
        stockDangerView.setOnClickListener(view -> {
            Intent intentList = new Intent(getContext(),StockTrackListActivity.class);
            intentList.putExtra("router",2);
            startActivity(intentList);
        });
    }
}