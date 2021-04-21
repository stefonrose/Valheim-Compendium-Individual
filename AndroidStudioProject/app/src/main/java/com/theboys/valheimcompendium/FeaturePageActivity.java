package com.theboys.valheimcompendium;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.theboys.valheimcompendium.fragments.BiomeFragment;
import com.theboys.valheimcompendium.fragments.CreatureFragment;
import com.theboys.valheimcompendium.fragments.ItemsFragment;
import com.theboys.valheimcompendium.fragments.MechanicsFragment;

public class FeaturePageActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView ;
    final FragmentManager fragmentManager = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feature_page);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuitem) {

                Fragment fragment ;
                switch (menuitem.getItemId()) {

                    case R.id.action_mechanics:
                        Toast.makeText(FeaturePageActivity.this, "mechanics", Toast.LENGTH_SHORT).show();
                        fragment = new MechanicsFragment();
                        break;

                    case R.id.action_items:
                        Toast.makeText(FeaturePageActivity.this, "items", Toast.LENGTH_SHORT).show();
                        fragment = new ItemsFragment();
                        break;

                    case R.id.action_creatures:
                        Toast.makeText(FeaturePageActivity.this, "creatures", Toast.LENGTH_SHORT).show();
                        fragment = new CreatureFragment();
                        break;

                    case R.id.action_biomes:
                        Toast.makeText(getBaseContext(), "Biomes", Toast.LENGTH_SHORT).show();
                        fragment = new BiomeFragment();
                        break;

                    default:
                        Toast.makeText(FeaturePageActivity.this, "Biomes", Toast.LENGTH_SHORT).show();
                        fragment = new BiomeFragment();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flcontainer, fragment).commit();
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_biomes);
    }


}