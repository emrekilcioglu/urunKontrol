package com.example.urunkontrol.employee;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urunkontrol.R;
import com.example.urunkontrol.classes.ApiUtils;
import com.example.urunkontrol.classes.ProductMovement;
import com.example.urunkontrol.classes.ProductMovementDaoInterfaca;
import com.example.urunkontrol.classes.ProductMovementResponse;
import com.example.urunkontrol.classes.RvAdapterTransHistory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TransactionHistoryFragment extends Fragment {
    private RecyclerView recyclerViewHistory;
    private ProductMovementDaoInterfaca productMovementDif;
    private String userId;
    private RvAdapterTransHistory adapterTransHistory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_transaction_history, container, false);
        recyclerViewHistory = rootView.findViewById(R.id.recylerViewHistory);
        recyclerViewHistory.setHasFixedSize(true);
        recyclerViewHistory.setLayoutManager(new LinearLayoutManager(getContext()));
        productMovementDif = ApiUtils.getProductMovementInterface();
        userId = getArguments().getString("user_id",null);

        productMovementDif.transHistory(userId).enqueue(new Callback<ProductMovementResponse>() {
            @Override
            public void onResponse(Call<ProductMovementResponse> call, Response<ProductMovementResponse> response) {
                List<ProductMovement> productMovementList = response.body().getProductMovement();
                adapterTransHistory = new RvAdapterTransHistory(getContext(),productMovementList);
                recyclerViewHistory.setAdapter(adapterTransHistory);

            }

            @Override
            public void onFailure(Call<ProductMovementResponse> call, Throwable t) {

            }
        });

        return rootView ;

    }
}