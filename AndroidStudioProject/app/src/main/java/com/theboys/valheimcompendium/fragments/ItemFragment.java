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
import com.theboys.valheimcompendium.ItemFragmentAdapter;
import com.theboys.valheimcompendium.FeaturePageActivity;
import com.theboys.valheimcompendium.ParseQueries;
import com.theboys.valheimcompendium.R;
import com.theboys.valheimcompendium.models.Feature;
import com.theboys.valheimcompendium.models.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemFragment extends Fragment {

    public static final String TAG = "ItemFragment";
    private RecyclerView rvItems;
    private TextView overviewTV;
    private ImageView imageIV;
    private ItemFragmentAdapter adapter;
    private List<Item> allItems;

    public ItemFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((FeaturePageActivity) getActivity()).setActionBarTitle("Items");
        return inflater.inflate(R.layout.fragment_items, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Feature feat = null;
        List<Feature> feats = ParseQueries.queryFeature();
        for(Feature feature: feats) {
            if (feature.getName().equals("Items")) {
                feat = feature;
            }
        }

        rvItems = view.findViewById(R.id.rvItems);
        overviewTV = view.findViewById(R.id.item_overviewTV);
        imageIV = view.findViewById(R.id.item_imageIV);

        overviewTV.setText(feat.getOverview());
        ParseFile image = feat.getImage();
        if (image != null) {
            Glide.with(getContext()).load(image.getUrl()).into(imageIV);
        }

        allItems = new ArrayList<>();
        adapter = new ItemFragmentAdapter(getContext(), allItems);

        rvItems.setAdapter(adapter);
        rvItems.setLayoutManager(new LinearLayoutManager(getContext()));

        queryItems();
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
                allItems.addAll(items);
                adapter.notifyDataSetChanged();

            }
        });
    }
}