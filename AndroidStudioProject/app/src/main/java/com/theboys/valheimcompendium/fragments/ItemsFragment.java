package com.theboys.valheimcompendium.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.theboys.valheimcompendium.Item;
import com.theboys.valheimcompendium.ItemsAdapter;
import com.theboys.valheimcompendium.R;

import java.util.ArrayList;
import java.util.List;

public class ItemsFragment extends Fragment {

    public static final String TAG = "ItemsFragment";
    private SearchView searchView;
    private RecyclerView itemsRV;
    protected ItemsAdapter adapter;
    protected List<Item> allItems;

    public ItemsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_items, container, false);

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemsRV = view.findViewById(R.id.itemsRV);



        allItems = new ArrayList<>();
        adapter = new ItemsAdapter(getContext(), allItems);

        // Steps to use the recycler view:
        // 0. create layout for one row in the list
        // 1. create the adapter
        // 2. create the data source
        // 3. set the adapter on the recycler view
        itemsRV.setAdapter(adapter);
        // 4. set the layout manager on the recycler view
        itemsRV.setLayoutManager(new LinearLayoutManager(getContext()));
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
                    Log.i(TAG, "Item: " + item.getItemName());
                }
                allItems.addAll(items);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        // New
        //SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });

    }


}