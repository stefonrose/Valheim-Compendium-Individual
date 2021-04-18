package com.theboys.valheimcompendium;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class HomePageActivity extends AppCompatActivity {

    protected List<Feature> allFeatures;
    public static final String TAG = "HomePageActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_page);
        RecyclerView featuresRV = findViewById(R.id.featuresRV);
        allFeatures = new ArrayList<>();

        // Create the adapter
        HomeFeatureAdapter featuresAdapter =  new HomeFeatureAdapter(this, allFeatures);

        // Set the adapter on the recycler view
        featuresRV.setAdapter(featuresAdapter);

        // Set a layout manager on the recycler view
        featuresRV.setLayoutManager(new LinearLayoutManager(this));
        queryFeature();
    }




    protected void queryFeature() {
        ParseQuery<Feature> query = ParseQuery.getQuery(Feature.class);
        query.findInBackground(new FindCallback<Feature>() {
            @Override
            public void done(List<Feature> features, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting features", e);
                    return;
                }
                for (Feature feature: features) {
                    Log.i(TAG, "Feature: " + feature.getFeatureName());
                }
                allFeatures.addAll(features);
                //featuresAdapter.notifyDataSetChanged();

            }
        });
    }
}