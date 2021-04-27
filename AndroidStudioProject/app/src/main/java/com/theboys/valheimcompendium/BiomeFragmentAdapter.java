package com.theboys.valheimcompendium;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import com.theboys.valheimcompendium.models.Biome;

import java.util.List;

public class BiomeFragmentAdapter extends RecyclerView.Adapter<BiomeFragmentAdapter.ViewHolder>{

    private Context context;
    private List<Biome> biomes;


    public BiomeFragmentAdapter(Context context, List<Biome> biomes) {
        this.context = context;
        this.biomes = biomes;
    }

    @NonNull
    @Override
    public BiomeFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BiomeFragmentAdapter.ViewHolder holder, int position) {
        Biome biome = biomes.get(position);
        holder.bind(biome);
    }

    @Override
    public int getItemCount() {
        return biomes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemIV;
        private TextView itemTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemIV = itemView.findViewById(R.id.itemIV);
            itemTV = itemView.findViewById(R.id.itemTV);
        }

        public void bind(Biome biome) {
            itemTV.setText(biome.getName());
            ParseFile image = biome.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(itemIV);
            }
        }

    }
}
