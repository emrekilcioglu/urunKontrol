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
import com.example.urunkontrol.classes.User;
import com.example.urunkontrol.employee.EmployeeChoiceActivity;
import com.example.urunkontrol.manager.ManagerChoiceActivity;
import com.example.urunkontrol.manager.ProductAddActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReadQrFragment extends Fragment {
    private Button buttonReadQr;
    private ProductDaoInterface productDif;
    private Intent intentChoice;
    private String jobStatus;
    private String userId;
    public ReadQrFragment(){
        Log.e("Constr","COnst Çalıştır");
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_read_qr, container, false);
        buttonReadQr  = rootView.findViewById(R.id.buttonReadQr);
        //Activity den aldığımız veriler
        jobStatus = getArguments().getString("job_status",null);
        userId = getArguments().getString("user_id",null);

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
        String qrBarcode = result.getContents();//Qr verimiz burada

        if (qrBarcode != null){
            productDif = ApiUtils.getProductInterface();
            productDif.qrControl(qrBarcode).enqueue(new Callback<ProductResponse>() {
                @Override
                public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                    Log.e("Retrofit","Retro Çlıştır");
                    if (response.body().getSuccess() == 1){
                        intentRouter(jobStatus,qrBarcode,userId);
                    }
                    else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());//Dialog nesnemiz
                        switch (jobStatus){
                            case "0":
                                builder.setTitle(getString(R.string.dialog_title));
                                builder.setMessage("Böyle bir ürün bulunamadı");//Veri burada
                                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).show();
                                break;
                            case "1":
                                builder.setTitle(getString(R.string.dialog_title));
                                builder.setMessage("Böyle bir ürün bulunamadı,ürün eklemek ister misiniz?");
                                builder.setPositiveButton("Ekle", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        // TODO: 21/12/2022 Buraya ürün eklmeme sayfasına geçiş ekle
                                        startActivity(new Intent(getContext(), ProductAddActivity.class));
                                    }
                                }).show();
                                builder.setNegativeButton("Ekleme", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        dialogInterface.dismiss();
                                    }
                                }).show();



                        }



                    }


                }

                @Override
                public void onFailure(Call<ProductResponse> call, Throwable t) {
                    Log.e("Hata",t.getMessage());


                }
            });







        }
    });//Burada da gelen veriyle alakalı işlemler yapıyoruz

    private void intentRouter(String jobStatus,String qrBarcode,String userId){
        Intent intent;
        switch (jobStatus){
            case "0":
                intent = new Intent(getContext(),EmployeeChoiceActivity.class);
                intent.putExtra("user_id",userId);
                intent.putExtra("qr_barcode",qrBarcode);
                startActivity(intent);
                break;
            case "1":
                intent = new Intent(getContext(),ManagerChoiceActivity.class);
                intent.putExtra("user_id",userId);
                intent.putExtra("qr_barcode",qrBarcode);
                startActivity(intent);

                break;

        }



    }




}