package com.menasy.recviewlandbook;

import java.io.Serializable;

public class Land implements Serializable
{
    String name;
    String country;
    int imgNo;

    public Land(String name, String country, int imgNo)
    {
        this.name = name;
        this.country = country;
        this.imgNo = imgNo;
    }
}
