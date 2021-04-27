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
import com.theboys.valheimcompendium.BiomeFragmentAdapter;
import com.theboys.valheimcompendium.CreatureFragmentAdapter;
import com.theboys.valheimcompendium.FeaturePageActivity;
import com.theboys.valheimcompendium.ParseQueries;
import com.theboys.valheimcompendium.R;
import com.theboys.valheimcompendium.models.Biome;
import com.theboys.valheimcompendium.models.Creature;
import com.theboys.valheimcompendium.models.Feature;

import java.util.ArrayList;
import java.util.List;


public class CreatureFragment extends Fragment {

    public static final String TAG = "CreatureFragment";
    private RecyclerView rvCreatures;
    private TextView overviewTV;
    private ImageView imageIV;
    private CreatureFragmentAdapter adapter;
    private List<Creature> allCreatures;

    public CreatureFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((FeaturePageActivity) getActivity()).setActionBarTitle("Creatures");
        return inflater.inflate(R.layout.fragment_creature, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Feature feat = null;
        List<Feature> feats = ParseQueries.queryFeature();
        for(Feature feature: feats) {
            if (feature.getName().equals("Creatures")) {
                feat = feature;
            }
        }

        rvCreatures = view.findViewById(R.id.rvCreatures);
        overviewTV = view.findViewById(R.id.creature_overviewTV);
        imageIV = view.findViewById(R.id.creature_imageIV);

        overviewTV.setText(feat.getOverview());
        ParseFile image = feat.getImage();
        if (image != null) {
            Glide.with(getContext()).load(image.getUrl()).into(imageIV);
        }

        allCreatures = new ArrayList<>();
        adapter = new CreatureFragmentAdapter(getContext(), allCreatures);

        rvCreatures.setAdapter(adapter);
        rvCreatures.setLayoutManager(new LinearLayoutManager(getContext()));

        queryCreatures();
    }

    protected void queryCreatures() {
        ParseQuery<Creature> query = ParseQuery.getQuery(Creature.class);
        query.findInBackground(new FindCallback<Creature>() {
            @Override
            public void done(List<Creature> creatures, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting creatures", e);
                    return;
                }
                for (Creature creature: creatures) {
                    Log.i(TAG, "Creature: " + creature.getName());
                }
                allCreatures.addAll(creatures);
                adapter.notifyDataSetChanged();

            }
        });
    }

}