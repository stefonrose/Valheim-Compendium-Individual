package com.theboys.valheimcompendium;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter {

    private Context context;
    private List<Feature> featureList;
    Feature feature;


    public MyAdapter(Context context, int textViewResourceId, ArrayList features) {
        super(context, textViewResourceId, features);
        featureList = features;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.home_grid_view_items, null);
        TextView feature_nameTV = (TextView) v.findViewById(R.id.feature_nameTV);
        ImageView featureIV = (ImageView) v.findViewById(R.id.featureIV);
        feature_nameTV.setText(feature.getFeatureName());
        ParseFile image = feature.getFeatureImage();
        if (image != null) {
            Glide.with(context).load(image.getUrl()).into(featureIV);
        }
        return v;

    }

}


