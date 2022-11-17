package com.example.urunkontrol.manager;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.urunkontrol.R;
import com.example.urunkontrol.classes.ApiUtils;
import com.example.urunkontrol.classes.BrandDaoInterface;
import com.example.urunkontrol.classes.Category;
import com.example.urunkontrol.classes.CategoryDaoInterface;
import com.example.urunkontrol.classes.CategoryResponse;
import com.example.urunkontrol.classes.RvAdapterCategory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListFragment extends Fragment {
    private FloatingActionButton floatButtonPool;
    private Intent intent;
    private BrandDaoInterface brandDif;
    //private CategoryDaoInterface categoryDif;
    private List<Category> categories;
    private RecyclerView recyclerViewPool;
    private RvAdapterCategory rvAdapterCategory;
    private RecyclerView.Adapter adapter;
    public ListFragment(RecyclerView.Adapter adapter){
        this.adapter = adapter;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //categoryDif = ApiUtils.getCategoryInterface();

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list, container, false);
        floatButtonPool = rootView.findViewById(R.id.floatButtonPool);
        recyclerViewPool = rootView.findViewById(R.id.recyclerViewPool);
        recyclerViewPool.setHasFixedSize(true);
        recyclerViewPool.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewPool.setAdapter(adapter);


        intent = new Intent(getContext(),ProductAddActivity.class);
        //brandDif = ApiUtils.getBrandDaoInterface();//Apiye bağladık



        /*floatButtonPool.setOnClickListener(view -> {
            startActivity(intent);

        });*/
        //tumKategori();
        // TODO: 16/11/2022 Bütün elemanlar için adapter bağla ve listlele 


        return  rootView;

    }
    public void tumKategori(){

    }

}