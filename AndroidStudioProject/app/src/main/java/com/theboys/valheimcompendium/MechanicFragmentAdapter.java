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
import com.theboys.valheimcompendium.models.Concept;

import java.util.List;

public class MechanicFragmentAdapter extends RecyclerView.Adapter<MechanicFragmentAdapter.ViewHolder>{

    private Context context;
    private List<Concept> mechanics;


    public MechanicFragmentAdapter(Context context, List<Concept> mechanics) {
        this.context = context;
        this.mechanics = mechanics;
    }

    @NonNull
    @Override
    public MechanicFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_item_view, parent, false);
        return new MechanicFragmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MechanicFragmentAdapter.ViewHolder holder, int position) {
        Concept mechanic = mechanics.get(position);
        holder.bind(mechanic);
    }

    @Override
    public int getItemCount() {
        return mechanics.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemIV;
        private TextView itemTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemIV = itemView.findViewById(R.id.itemIV);
            itemTV = itemView.findViewById(R.id.itemTV);
        }

        public void bind(Concept mechanic) {
            itemTV.setText(mechanic.getName());
            ParseFile image = mechanic.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(itemIV);
            }
        }

    }

}
