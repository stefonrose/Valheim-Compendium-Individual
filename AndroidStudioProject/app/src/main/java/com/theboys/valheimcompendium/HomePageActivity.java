package com.theboys.valheimcompendium;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.JsonArray;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.theboys.valheimcompendium.adapters.HomeFeatureAdapter;
import com.theboys.valheimcompendium.models.Feature;

import org.json.JSONArray;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HomePageActivity extends AppCompatActivity {

    protected List<Feature> allFeatures;
    public static final String TAG = "HomePageActivity";
    HomeFeatureAdapter featuresAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_entry_page);
        RecyclerView featuresRV = findViewById(R.id.featuresRV);
        allFeatures = new ArrayList<>();

        // Create the adapter
        featuresAdapter =  new HomeFeatureAdapter(this, allFeatures);

        // Set the adapter on the recycler view
        featuresRV.setAdapter(featuresAdapter);

        // Set a layout manager on the recycler view
        featuresRV.setLayoutManager(new LinearLayoutManager(this));
        queryFeature();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search) {

            return true;
        } else if (item.getItemId() == R.id.index) {

            Intent i = new Intent(HomePageActivity.this, IndexPageActivity.class);
            startActivity(i);
            return true;
        } else if (item.getItemId() == R.id.reddit) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
                    Log.i(TAG, "Feature: " + feature.getName());
                }
                allFeatures.addAll(features);
                featuresAdapter.notifyDataSetChanged();

            }
        });
    }
}