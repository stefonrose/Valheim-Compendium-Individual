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
import com.theboys.valheimcompendium.models.Creature;

import java.util.List;

public class CreatureFragmentAdapter extends RecyclerView.Adapter<CreatureFragmentAdapter.ViewHolder>{

    private Context context;
    private List<Creature> creatures;


    public CreatureFragmentAdapter(Context context, List<Creature> creatures) {
        this.context = context;
        this.creatures = creatures;
    }

    @NonNull
    @Override
    public CreatureFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_item_view, parent, false);
        return new CreatureFragmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CreatureFragmentAdapter.ViewHolder holder, int position) {
        Creature creature = creatures.get(position);
        holder.bind(creature);
    }

    @Override
    public int getItemCount() {
        return creatures.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemIV;
        private TextView itemTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemIV = itemView.findViewById(R.id.itemIV);
            itemTV = itemView.findViewById(R.id.itemTV);
        }

        public void bind(Creature creature) {
            itemTV.setText(creature.getName());
            ParseFile image = creature.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(itemIV);
            }
        }

    }
    
}
