package com.example.urunkontrol;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.urunkontrol.classes.ApiUtils;
import com.example.urunkontrol.classes.CaptureAct;
import com.example.urunkontrol.classes.Product;
import com.example.urunkontrol.classes.ProductDaoInterface;
import com.example.urunkontrol.classes.ProductResponse;
import com.example.urunkontrol.employee.EmployeeChoiceActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReadQrFragment extends Fragment {
    private Button buttonReadQr;
    private Intent intentChoice;
    private ProductDaoInterface productDif;
    public ReadQrFragment(){
        Log.e("Constr","COnst Çalıştır");
    }
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
        //asıl işler burada olacak çünkü veri burada
        String qrBarcode = result.getContents();

        if (qrBarcode != null){
            productDif = ApiUtils.getProductInterface();
            productDif.qrControl(qrBarcode).enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    Log.e("Retrofit","Retro Çlıştır");
                    if (response.body().getSuccess() == 1){

                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle(getString(R.string.dialog_title));
                        builder.setMessage("Böyle bir ürün bulunamadı");//Veri burada
                        builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();


                    }


                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {
                    Log.e("Hata",t.getMessage());


                }
            });







        }
    });//Burada da gelen veriyle alakalı işlemler yapıyoruz
}