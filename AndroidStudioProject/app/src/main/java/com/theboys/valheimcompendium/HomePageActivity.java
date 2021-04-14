package com.theboys.valheimcompendium;

import com.theboys.valheimcompendium.MyAdapter;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


public class HomePageActivity extends AppCompatActivity {

    GridView simpleList;
    ArrayList featureList = new ArrayList();

    public static final String TAG = "HomePageActivity";
    protected MyAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
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
                featureList.addAll(features);
                //adapter.notifyDataSetChanged();

            }
        });
    }
}