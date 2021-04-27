package com.theboys.valheimcompendium;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import com.theboys.valheimcompendium.models.Feature;

import org.parceler.Parcels;

import java.util.List;
public class    HomeFeatureAdapter extends RecyclerView.Adapter<HomeFeatureAdapter.ViewHolder> {

    private Context context;
    private List<Feature> features;


    public HomeFeatureAdapter(Context context, List<Feature> features) {
        this.context = context;
        this.features = features;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_feature_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Feature feature = features.get(position);
        holder.bind(feature);
    }

    // Clean all elements of the recycler
    public void clear() {
        features.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Feature> list) {
        features.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return features.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout container;
        private ImageView featureIV;
        private TextView feature_nameTV;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            featureIV = itemView.findViewById(R.id.featureIV);
            feature_nameTV = itemView.findViewById(R.id.feature_nameTV);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(Feature feature) {
            // Bind the posts data to the view elements
            feature_nameTV.setText(feature.getName());
            ParseFile image = feature.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(featureIV);
            }

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, FeaturePageActivity.class);
                    i.putExtra("feature", Parcels.wrap(feature));
                    context.startActivity(i);
                    //Toast.makeText(context, feature.getFeatureName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}


