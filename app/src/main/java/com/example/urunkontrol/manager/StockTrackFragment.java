package com.example.urunkontrol.manager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.urunkontrol.R;


public class StockTrackFragment extends Fragment {
    private ProgressBar progressBarStock;
    private TextView textViewStockPercent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_stock_track, container, false);
        progressBarStock = rootView.findViewById(R.id.progressBarStock);
        textViewStockPercent = rootView.findViewById(R.id.textViewStockPercent);
        return rootView;
    }
}