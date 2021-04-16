package com.theboys.valheimcompendium;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<Feature> featureList;
    Feature feature;


    public MyAdapter(Context context, ArrayList features) {

        featureList = features;
    }

    @Override
    public int getCount() {
        return featureList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.home_grid_view_items, null);
        }

        ImageView bannerIV;
        TextView descriptionTV;

        ImageView biomeIV;
        TextView biomeTV;

        ImageView creatureIV;
        TextView creatureTV;

        ImageView itemsIV;
        TextView itemsTV;



//        TextView feature_nameTV = (TextView) convertView.findViewById(R.id.feature_nameTV);
//        ImageView featureIV = (ImageView) convertView.findViewById(R.id.featureIV);
//        feature_nameTV.setText(feature.getFeatureName());
//        ParseFile image = feature.getFeatureImage();
//        if (image != null) {
//            Glide.with(context).load(image.getUrl()).into(featureIV);
//        }
        return convertView;


//        View v = convertView;
//        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        v = inflater.inflate(R.layout.home_grid_view_items, null);
//        TextView feature_nameTV = (TextView) v.findViewById(R.id.feature_nameTV);
//        ImageView featureIV = (ImageView) v.findViewById(R.id.featureIV);
//        feature_nameTV.setText(feature.getFeatureName());
//        ParseFile image = feature.getFeatureImage();
//        if (image != null) {
//            Glide.with(context).load(image.getUrl()).into(featureIV);
//        }
//        return v;

    }
}


