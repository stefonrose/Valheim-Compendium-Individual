package com.theboys.valheimcompendium;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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

        allEntries.addAll(ParseQueries.queryEntry());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_index, menu);
        MenuItem searchItem = menu.findItem(R.id.search);

        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.index_sort) {
            Toast.makeText(this, "Sort!", Toast.LENGTH_SHORT);
            Collections.reverse(allEntries);
            indexAdapter.notifyDataSetChanged();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<Entry> filteredList = new ArrayList<>();

        // running a for loop to compare elements.
        for (Entry item : allEntries) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            indexAdapter.filterList(filteredList);
        }
    }
}