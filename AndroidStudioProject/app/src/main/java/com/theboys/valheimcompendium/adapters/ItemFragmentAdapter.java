package com.theboys.valheimcompendium.adapters;

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
import com.theboys.valheimcompendium.R;
import com.theboys.valheimcompendium.models.Item;

import java.util.List;

public class ItemFragmentAdapter extends RecyclerView.Adapter<ItemFragmentAdapter.ViewHolder>{

    private Context context;
    private List<Item> items;


    public ItemFragmentAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ItemFragmentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_item_view, parent, false);
        return new ItemFragmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemFragmentAdapter.ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView itemIV;
        private TextView itemTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemIV = itemView.findViewById(R.id.itemIV);
            itemTV = itemView.findViewById(R.id.itemTV);
        }

        public void bind(Item item) {
            itemTV.setText(item.getName());
            ParseFile image = item.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(itemIV);
            }
        }

    }

}
