package com.example.urunkontrol;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.urunkontrol.classes.CaptureAct;
import com.example.urunkontrol.employee.EmployeeChoiceActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;


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
            scanCode();
        });

        return rootView;

    }
    private void scanCode() {//Burada qr okuyucuyla alakalı ayarları yapıyoruz
        ScanOptions options =new ScanOptions();
        options.setPrompt(getString(R.string.flash_info));//prompt bilgi demek
        options.setBeepEnabled(true);//Beep ses manasında
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }
    ActivityResultLauncher<ScanOptions> barLauncher =registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() !=null){

            result.getContents();//Veri burada
            // TODO: 20/11/2022 Qr bağladın ama iç işlemleri şu an boş 

        }
    });//Burada da gelen veriyle alakalı işlemler yapıyoruz
}