package com.theboys.valheimcompendium;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.theboys.valheimcompendium.models.Biome;
import com.theboys.valheimcompendium.models.Creature;
import com.theboys.valheimcompendium.models.Feature;
import com.theboys.valheimcompendium.models.Item;

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

    public static List<Item> queryItems(){
        List<Item> allItems = new ArrayList<>();
        ParseQuery<Item> query = ParseQuery.getQuery(Item.class);
        try {
            allItems.addAll(query.find());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return allItems;
    }
}
