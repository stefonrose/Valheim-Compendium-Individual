package com.theboys.valheimcompendium;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.theboys.valheimcompendium.models.Feature;
import com.theboys.valheimcompendium.models.Item;

import java.util.ArrayList;
import java.util.List;


public class HomePageActivity extends AppCompatActivity {

    protected List<Feature> allFeatures;
    public static final String TAG = "HomePageActivity";
    HomeFeatureAdapter featuresAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        queryItems();
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

    protected void queryItems() {
        ParseQuery<Item> query = ParseQuery.getQuery(Item.class);
        query.findInBackground(new FindCallback<Item>() {
            @Override
            public void done(List<Item> items, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting items", e);
                    return;
                }
                for (Item item: items) {
                    Log.i(TAG, "Item: " + item.getName());
                }
                //allItems.addAll(items);
                //featuresAdapter.notifyDataSetChanged();

            }
        });
    }
}