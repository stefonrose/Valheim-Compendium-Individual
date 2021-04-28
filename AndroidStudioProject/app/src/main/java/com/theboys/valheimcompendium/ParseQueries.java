package com.theboys.valheimcompendium;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.theboys.valheimcompendium.models.Biome;
import com.theboys.valheimcompendium.models.Concept;
import com.theboys.valheimcompendium.models.Creature;
import com.theboys.valheimcompendium.models.Entry;
import com.theboys.valheimcompendium.models.Feature;
import com.theboys.valheimcompendium.models.Item;
import com.theboys.valheimcompendium.tools.NameSorter;

import java.util.ArrayList;
import java.util.List;

public class ParseQueries {

    static String TAG = "ParseQueries";

    public static List<Feature> queryFeature(){
        List<Feature> allFeatures = new ArrayList<>();
        ParseQuery<Feature> query = ParseQuery.getQuery(Feature.class);
        try {
            List<Feature> features = query.find();
            allFeatures.addAll(features);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return allFeatures;
    }

    public static List<Biome> queryBiome(){
        List<Biome> allBiomes = new ArrayList<>();
        ParseQuery<Biome> query = ParseQuery.getQuery(Biome.class);
        try {
            allBiomes.addAll(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return allBiomes;
    }

    public static List<Creature> queryCreature() {
        List<Creature> allCreatures = new ArrayList<>();
        ParseQuery<Creature> query = ParseQuery.getQuery(Creature.class);
        try {
            allCreatures.addAll(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return allCreatures;
    }

    public static List<Item> queryItem(){
        List<Item> allItems = new ArrayList<>();
        ParseQuery<Item> query = ParseQuery.getQuery(Item.class);
        try {
            allItems.addAll(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return allItems;
    }

    public static List<Entry> queryEntry() {
        List<Entry> allEntries = new ArrayList<>();

        ParseQuery<Biome> biomeQuery = ParseQuery.getQuery(Biome.class);
        try {
            allEntries.addAll(biomeQuery.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ParseQuery<Creature> creatureQuery = ParseQuery.getQuery(Creature.class);
        try {
            allEntries.addAll(creatureQuery.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ParseQuery<Item> itemQuery = ParseQuery.getQuery(Item.class);
        try {
            allEntries.addAll(itemQuery.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ParseQuery<Concept> mechanicQuery = ParseQuery.getQuery(Concept.class);
        try {
            allEntries.addAll(mechanicQuery.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        allEntries.sort(new NameSorter());
        return allEntries;
    }

//    protected void queryAll() {
//        ParseQuery<Biome> biomeQuery = ParseQuery.getQuery(Biome.class);
//        biomeQuery.findInBackground(new FindCallback<Biome>() {
//            @Override
//            public void done(List<Biome> biomes, ParseException e) {
//                if (e != null) {
//                    Log.e(TAG, "Issue with getting biomes", e);
//                    return;
//                }
//                allEntries.addAll(biomes);
//                indexAdapter.notifyDataSetChanged();
//
//            }
//        });
//
//        ParseQuery<Creature> creatureQuery = ParseQuery.getQuery(Creature.class);
//        creatureQuery.findInBackground(new FindCallback<Creature>() {
//            @Override
//            public void done(List<Creature> creatures, ParseException e) {
//                if (e != null) {
//                    Log.e(TAG, "Issue with getting creatures", e);
//                    return;
//                }
//                allEntries.addAll(creatures);
//                indexAdapter.notifyDataSetChanged();
//
//            }
//        });
//
//        ParseQuery<Item> itemQuery = ParseQuery.getQuery(Item.class);
//        itemQuery.findInBackground(new FindCallback<Item>() {
//            @Override
//            public void done(List<Item> items, ParseException e) {
//                if (e != null) {
//                    Log.e(TAG, "Issue with getting items", e);
//                    return;
//                }
//                allEntries.addAll(items);
//                indexAdapter.notifyDataSetChanged();
//
//            }
//        });
//
//        ParseQuery<Concept> mechanicQuery = ParseQuery.getQuery(Concept.class);
//        mechanicQuery.findInBackground(new FindCallback<Concept>() {
//            @Override
//            public void done(List<Concept> mechanics, ParseException e) {
//                if (e != null) {
//                    Log.e(TAG, "Issue with getting mechanics", e);
//                    return;
//                }
//                allEntries.addAll(mechanics);
//                indexAdapter.notifyDataSetChanged();
//
//            }
//        });
//
//        allEntries.sort(new NameSorter());
//        indexAdapter.notifyDataSetChanged();
//
//    }
}
