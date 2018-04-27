package org.brohede.marcus.fragmentsapp;

/**
 * Created by marcus on 2018-04-25.
 */

public class Mountain {

    private String name;
    private int height;
    private String imageUrl;
    private String location;
    private String wikipediaPage;

    public Mountain(String name, int height, String location, String imageUrl, String wikipediaPage){
        this.name = name;
        this.height = height;
        this.location = location;
        this.imageUrl = imageUrl;
        this.wikipediaPage = wikipediaPage;
    }

    public String getName(){
        return name;
    }

    public int getHeight(){
        return height;
    }

    public String getLocation(){
        return location;
    }

    public String getImage(){
        return imageUrl;
    }

    public String getWikipediaPage(){
        return wikipediaPage;
    }

    public String toString(){
        return name;
    }

    public String infoText(){
        return name + " is a part of the " + location + " mountain range and is " + height + "m high.";
    }
}
