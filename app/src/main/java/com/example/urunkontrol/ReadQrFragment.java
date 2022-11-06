package com.example.urunkontrol;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.urunkontrol.employee.EmployeeChoiceActivity;


public class ReadQrFragment extends Fragment {
    private Button buttonReadQr;
    private Intent intentChoice;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_read_qr, container, false);
        buttonReadQr  = rootView.findViewById(R.id.buttonReadQr);
        intentChoice = new Intent(getContext(), EmployeeChoiceActivity.class);
        buttonReadQr.setOnClickListener(view -> {
            startActivity(intentChoice);
        });

        return rootView;

    }
}