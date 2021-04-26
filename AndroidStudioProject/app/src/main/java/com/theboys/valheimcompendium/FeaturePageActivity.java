package com.theboys.valheimcompendium;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.theboys.valheimcompendium.fragments.BiomeFragment;
import com.theboys.valheimcompendium.fragments.CreatureFragment;
import com.theboys.valheimcompendium.fragments.ItemsFragment;
import com.theboys.valheimcompendium.fragments.MechanicsFragment;

import org.parceler.Parcels;

public class FeaturePageActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    public static final String TAG = "FeaturePageActivity";
    final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_page);

        Feature feat = Parcels.unwrap(getIntent().getParcelableExtra("feature"));
        String selected = feat.getFeatureName();

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {

                Fragment fragment ;
                switch (menuitem.getItemId()) {

                    case R.id.action_mechanics:
                        fragment = new MechanicsFragment();
                        break;

                    case R.id.action_items:
                        fragment = new ItemsFragment();
                        break;

                    case R.id.action_creatures:
                        fragment = new CreatureFragment();
                        break;

                    default:
                        fragment = new BiomeFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flcontainer, fragment).commit();
                return true;
            }
        });

        // Set default selection
        int defaultSelection;
        switch (selected) {
            case "Mechanics":
                defaultSelection = R.id.action_mechanics;
                break;
            case "Items":
                defaultSelection = R.id.action_items;
                break;
            case "Creatures":
                defaultSelection = R.id.action_creatures;
                break;
            default:
                defaultSelection = R.id.action_biomes;
                break;
        }
        bottomNavigationView.setSelectedItemId(defaultSelection);
    }
}