package com.theboys.valheimcompendium;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> implements Filterable {


    private Context context;
    private List<Item> items;
    List<Item> allItems;


    public ItemsAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
        this.allItems = new ArrayList<>(items);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_frag, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.bind(item);
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Item> list) {
        items.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() { return items.size(); }

    @Override
    public Filter getFilter() {
        return new Filter() {
            //return filter;
            //}

            //Filter filter = new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    allItems = items;
                } else {
                    List<Item> filteredList = new ArrayList<>();
                    for (Item item : items) {
                        if (item.getItemName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(item);
                        }
                    }
                    allItems = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = allItems;
                return filterResults;
            }


//            List<Item> filteredList = new ArrayList<>();
//
//            if (constraint == null || constraint.length() == 0) {
//                filteredList.addAll(allItems);
//            } else {
//                for (Item item: allItems) {
//                    System.out.println(constraint.toString());
//                    if (item.getItemName().toLowerCase().contains(constraint.toString().toLowerCase())) {
//                        filteredList.add(item);
//                    }
//                }
//            }
//
//            FilterResults filterResults = new FilterResults();
//            filterResults.values = filteredList;
//            return filterResults;
//        }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
//            allItems.clear();
//            allItems.addAll((Collection<? extends Item>) filterResults.values);
//            System.out.println(items);
//            notifyDataSetChanged();
                allItems = (ArrayList<Item>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView item_nameTV;
        private ImageView itemIV;
        private TextView item_overviewTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_nameTV = itemView.findViewById(R.id.item_nameTV);
            itemIV = itemView.findViewById(R.id.itemIV);
            item_overviewTV = itemView.findViewById(R.id.item_overviewTV);
        }

        public void bind(Item item) {
            item_nameTV.setText(item.getItemName());
            item_overviewTV.setText(item.getItemOverview());
            ParseFile image = item.getItemImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(itemIV);
            }

        }

    }
}
