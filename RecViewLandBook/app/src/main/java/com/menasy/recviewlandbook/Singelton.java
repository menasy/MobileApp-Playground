package com.menasy.recviewlandbook;

public class Singelton {

    private Land selectLand;
    private static Singelton singelton = null;

    private Singelton(){

    }
    public void setLand(Land obj)
    {
        this.selectLand = obj;
    }
    public Land getLand()
    {
        return this.selectLand;
    }
    public static Singelton getInstance()
    {
        if (singelton == null)
            singelton = new Singelton();
        return singelton;
    }

}
