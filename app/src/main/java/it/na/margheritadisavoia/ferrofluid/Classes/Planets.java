package it.na.margheritadisavoia.ferrofluid.Classes;


/**
 * Created by pe-la on 18/10/2017.
 */

public class Planets {

    public String planetName;
    public int planetImage;


    public Planets(String n, int i){

        planetName = n;
        planetImage = i;

    }
    public String getPlanetName(){

        return planetName;

    }

    public int getPlanetImage(){

        return planetImage;
    }

}
