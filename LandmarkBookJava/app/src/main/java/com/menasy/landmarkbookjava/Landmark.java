package com.menasy.landmarkbookjava;

import java.io.Serializable;

public class Landmark implements Serializable
{
    String name;
    String country;
    int image;
    Landmark(String name, String contry, int image)
    {
        this.name = name;
        this.country = contry;
        this.image = image;
    }
}
