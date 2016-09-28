package controller;

import domain.Coordinate;

public class AristaCoordinates
{ 
    public final Coordinate coord1; 
    public final Coordinate coord2;
    public double dist;
    
    public AristaCoordinates(Coordinate coord1, Coordinate coord2)
    { 
        this.coord1 = coord1; 
        this.coord2 = coord2; 
        dist= Coordinates.distanceCoord(coord1,coord2);
    }
}
