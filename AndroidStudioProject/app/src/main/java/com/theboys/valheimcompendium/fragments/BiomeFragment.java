package com.theboys.valheimcompendium.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.theboys.valheimcompendium.adapters.BiomeFragmentAdapter;
import com.theboys.valheimcompendium.FeaturePageActivity;
import com.theboys.valheimcompendium.ParseQueries;
import com.theboys.valheimcompendium.R;
import com.theboys.valheimcompendium.models.Biome;
import com.theboys.valheimcompendium.models.Feature;

import java.util.ArrayList;
import java.util.List;


public class BiomeFragment extends Fragment {

    public static final String TAG = "BiomeFragment";
    private RecyclerView rvBiomes;
    private TextView overviewTV;
    private ImageView imageIV;
    private BiomeFragmentAdapter adapter;
    private List<Biome> allBiomes;

    public BiomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((FeaturePageActivity) getActivity()).setActionBarTitle("Biomes");
        return inflater.inflate(R.layout.fragment_biome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Feature feat = null;
        List<Feature> feats = ParseQueries.queryFeature();
        for(Feature feature: feats) {
            if (feature.getName().equals("Biomes")) {
                feat = feature;
            }
        }

        rvBiomes = view.findViewById(R.id.rvBiomes);
        overviewTV = view.findViewById(R.id.biome_overviewTV);
        imageIV = view.findViewById(R.id.biome_imageIV);


        overviewTV.setText(feat.getOverview());
        ParseFile image = feat.getImage();
        if (image != null) {
            Glide.with(getContext()).load(image.getUrl()).into(imageIV);
        }

        allBiomes = new ArrayList<>();
        adapter = new BiomeFragmentAdapter(getContext(), allBiomes);

        rvBiomes.setAdapter(adapter);
        rvBiomes.setLayoutManager(new LinearLayoutManager(getContext()));

        queryBiomes();
    }

    protected void queryBiomes() {
        ParseQuery<Biome> query = ParseQuery.getQuery(Biome.class);
        query.findInBackground(new FindCallback<Biome>() {
            @Override
            public void done(List<Biome> biomes, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting biomes", e);
                    return;
                }
                for (Biome biome: biomes) {
                    Log.i(TAG, "Biome: " + biome.getName());
                }
                allBiomes.addAll(biomes);
                adapter.notifyDataSetChanged();

            }
        });
    }

}