package com.example.urunkontrol.manager;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urunkontrol.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ProductListFragment extends Fragment {
    private FloatingActionButton floatButtonPool;
    private Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_product_list, container, false);
        floatButtonPool = rootView.findViewById(R.id.floatButtonPool);
        intent = new Intent(getContext(),ProductAddActivity.class);
        floatButtonPool.setOnClickListener(view -> {
            startActivity(intent);

        });
        return  rootView;

    }
}