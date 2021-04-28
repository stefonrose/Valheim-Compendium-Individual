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
import com.theboys.valheimcompendium.models.Entry;
import com.theboys.valheimcompendium.models.Feature;

import java.util.List;

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHolder> {

    private Context context;
    private List<Entry> entries;

    public IndexAdapter(Context context, List<Entry> entries) {
        this.context = context;
        this.entries = entries;
    }

    @NonNull
    @Override
    public IndexAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.index_entry_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IndexAdapter.ViewHolder holder, int position) {
        Entry entry = entries.get(position);
        holder.bind(entry);
    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView entry_imageIV;
        TextView entry_titleTV;
        TextView entry_overviewTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            entry_imageIV = itemView.findViewById(R.id.entry_imageIV);
            entry_titleTV = itemView.findViewById(R.id.entry_titleTV);
            entry_overviewTV = itemView.findViewById(R.id.entry_overviewTV);

        }

        public void bind(Entry entry) {
            entry_titleTV.setText(entry.getName());
            entry_overviewTV.setText(entry.getOverview());
            ParseFile image = entry.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(entry_imageIV);
            }
        }
    }
}
