package com.example.urunkontrol.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.urunkontrol.R;

import java.util.ArrayList;
import java.util.List;

public class BrandAdapter extends  ArrayAdapter<Brand> {
    private Context mcontext;
    int res;
    private List<Brand> brandsAll;

    public BrandAdapter(@NonNull Context context, int resource, List<Brand> brands) {
        super(context, resource,brands);
        this.mcontext = context;
        this.res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String categoryName = getItem(position).getBrandName();
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(res,parent,false);
        TextView textViewCategories = convertView.findViewById(R.id.textViewCategories);
        textViewCategories.setText(categoryName);
        return convertView;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            FilterResults results = new FilterResults();
            List<Brand> suggestion = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0){
                suggestion.addAll(brandsAll);
            }
            else {
                String filterText = charSequence.toString().toLowerCase().trim();
                for (Brand c:brandsAll){
                    if (c.getBrandName().toLowerCase().contains(filterText)){
                        suggestion.add(c);
                    }
                }
            }
            results.values = suggestion;
            results.count = suggestion.size();
            return results;

        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            clear();
            addAll((List) filterResults.values);
            notifyDataSetChanged();

        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((Brand) resultValue).getBrandName();
        }
    };

    @NonNull
    @Override
    public Filter getFilter() {
        return this.filter;
    }
}
