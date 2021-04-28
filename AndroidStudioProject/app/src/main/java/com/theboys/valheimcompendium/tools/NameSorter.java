package com.theboys.valheimcompendium.tools;
import com.theboys.valheimcompendium.models.Entry;

import java.util.Comparator;

public class NameSorter implements Comparator<Entry>{
    @Override
    public int compare(Entry o1, Entry o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
