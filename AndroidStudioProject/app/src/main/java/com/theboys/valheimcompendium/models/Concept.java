package com.theboys.valheimcompendium.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

import org.parceler.Parcel;

@Parcel(analyze={Concept.class, Entry.class})
@ParseClassName("Concept")
public class Concept extends Entry {

    public Concept() {}

}
