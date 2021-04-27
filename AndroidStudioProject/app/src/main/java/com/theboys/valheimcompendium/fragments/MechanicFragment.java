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
import com.theboys.valheimcompendium.MechanicFragmentAdapter;
import com.theboys.valheimcompendium.FeaturePageActivity;
import com.theboys.valheimcompendium.ParseQueries;
import com.theboys.valheimcompendium.R;
import com.theboys.valheimcompendium.models.Concept;
import com.theboys.valheimcompendium.models.Creature;
import com.theboys.valheimcompendium.models.Feature;

import java.util.ArrayList;
import java.util.List;

public class MechanicFragment extends Fragment {

    public static final String TAG = "MechanicFragment";
    private RecyclerView rvMechanics;
    private TextView overviewTV;
    private ImageView imageIV;
    private MechanicFragmentAdapter adapter;
    private List<Concept> allMechanics;

    public MechanicFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((FeaturePageActivity) getActivity()).setActionBarTitle("Mechanics");
        return inflater.inflate(R.layout.fragment_mechanics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Feature feat = null;
        List<Feature> feats = ParseQueries.queryFeature();
        for(Feature feature: feats) {
            if (feature.getName().equals("Mechanics")) {
                feat = feature;
            }
        }

        rvMechanics = view.findViewById(R.id.rvMechanics);
        overviewTV = view.findViewById(R.id.mechanic_overviewTV);
        imageIV = view.findViewById(R.id.mechanic_imageIV);

        overviewTV.setText(feat.getOverview());
        ParseFile image = feat.getImage();
        if (image != null) {
            Glide.with(getContext()).load(image.getUrl()).into(imageIV);
        }

        allMechanics = new ArrayList<>();
        adapter = new MechanicFragmentAdapter(getContext(), allMechanics);

        rvMechanics.setAdapter(adapter);
        rvMechanics.setLayoutManager(new LinearLayoutManager(getContext()));

        queryMechanics();
    }

    protected void queryMechanics() {
        ParseQuery<Concept> query = ParseQuery.getQuery(Concept.class);
        query.findInBackground(new FindCallback<Concept>() {
            @Override
            public void done(List<Concept> mechanics, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting mechanics", e);
                    return;
                }
                for (Concept mechanic: mechanics) {
                    Log.i(TAG, "Mechanic: " + mechanic.getName());
                }
                allMechanics.addAll(mechanics);
                adapter.notifyDataSetChanged();

            }
        });
    }
}