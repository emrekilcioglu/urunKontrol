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
import java.util.Locale;

public class CategoryAdapter extends  ArrayAdapter<Category> {
    private Context mcontext;
    int res;
    private List<Category> categoriesAll;

    public CategoryAdapter(@NonNull Context context, int resource, List<Category> categories) {
        super(context, resource,categories);
        this.mcontext = context;
        this.res = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String categoryName = getItem(position).getCategoryName();
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
            List<Category> suggestion = new ArrayList<>();
            if (charSequence == null || charSequence.length() == 0){
                suggestion.addAll(categoriesAll);
            }
            else {
                String filterText = charSequence.toString().toLowerCase().trim();
                for (Category c:categoriesAll){
                    if (c.getCategoryName().toLowerCase().contains(filterText)){
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
            return ((Category) resultValue).getCategoryName();
        }
    };

    @NonNull
    @Override
    public Filter getFilter() {
        return this.filter;
    }
}
