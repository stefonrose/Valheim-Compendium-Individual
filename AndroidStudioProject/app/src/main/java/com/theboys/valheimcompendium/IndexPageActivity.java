package com.theboys.valheimcompendium;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.theboys.valheimcompendium.adapters.IndexAdapter;
import com.theboys.valheimcompendium.models.Biome;
import com.theboys.valheimcompendium.models.Concept;
import com.theboys.valheimcompendium.models.Creature;
import com.theboys.valheimcompendium.models.Entry;
import com.theboys.valheimcompendium.models.Item;
import com.theboys.valheimcompendium.tools.NameSorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IndexPageActivity extends AppCompatActivity {

    public static final String TAG = "IndexPageActivity";
    protected List<Entry> allEntries;
    IndexAdapter indexAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_page);

        RecyclerView rvIndex = findViewById(R.id.rvIndex);
        allEntries = new ArrayList<>();

        indexAdapter = new IndexAdapter(this, allEntries);
        rvIndex.setAdapter(indexAdapter);

        rvIndex.setLayoutManager(new LinearLayoutManager(this));
        //queryAll();
        allEntries.addAll(ParseQueries.queryEntry());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_index, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Log.i(TAG, "Item: " + item.getItemId());
        Log.i(TAG, "Item: " + R.id.index_sort);
        if (item.getItemId() == R.id.index_search) {

            return true;
        } else if (item.getItemId() == R.id.index_index) {

            return true;
        } else if (item.getItemId() == R.id.index_sort) {
            Toast.makeText(this, "Sort!", Toast.LENGTH_SHORT);
            Collections.reverse(allEntries);
            indexAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void queryAll() {
        ParseQuery<Biome> biomeQuery = ParseQuery.getQuery(Biome.class);
        biomeQuery.findInBackground(new FindCallback<Biome>() {
            @Override
            public void done(List<Biome> biomes, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting biomes", e);
                    return;
                }
                allEntries.addAll(biomes);
                indexAdapter.notifyDataSetChanged();

            }
        });

        ParseQuery<Creature> creatureQuery = ParseQuery.getQuery(Creature.class);
        creatureQuery.findInBackground(new FindCallback<Creature>() {
            @Override
            public void done(List<Creature> creatures, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting creatures", e);
                    return;
                }
                allEntries.addAll(creatures);
                indexAdapter.notifyDataSetChanged();

            }
        });

        ParseQuery<Item> itemQuery = ParseQuery.getQuery(Item.class);
        itemQuery.findInBackground(new FindCallback<Item>() {
            @Override
            public void done(List<Item> items, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting items", e);
                    return;
                }
                allEntries.addAll(items);
                indexAdapter.notifyDataSetChanged();

            }
        });

        ParseQuery<Concept> mechanicQuery = ParseQuery.getQuery(Concept.class);
        mechanicQuery.findInBackground(new FindCallback<Concept>() {
            @Override
            public void done(List<Concept> mechanics, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Issue with getting mechanics", e);
                    return;
                }
                allEntries.addAll(mechanics);
                indexAdapter.notifyDataSetChanged();

            }
        });

        allEntries.sort(new NameSorter());
        indexAdapter.notifyDataSetChanged();

    }

}