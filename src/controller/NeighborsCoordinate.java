package controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import domain.Coordinate;

public class NeighborsCoordinate implements Iterable<Coordinate> {
	private Coordinate coord;
	private Set<Coordinate> neighborsCoordinate;
	
	public NeighborsCoordinate(Coordinate coord)
	{
		this.coord = coord;
		neighborsCoordinate = new HashSet<Coordinate>();
	}
	
	public void addNeighbor(Coordinate otherCoord)
	{
		neighborsCoordinate.add(otherCoord);
	}
	
	public void removeNeighbor(Coordinate otherCoord)
	{
		neighborsCoordinate.remove(otherCoord);
	}
	
	public int size()
	{
		return neighborsCoordinate.size();
	}
	
	public boolean contains(Coordinate coord)
	{
		return neighborsCoordinate.contains(coord);
	}
	
	public boolean equals(Coordinate coord)
	{
		return this.coord.equals(coord);
	}
	
	public double getPeso(Coordinate coord)
	{
		return Coordinates.getPeso(this.coord, coord);
	}
	
	public Coordinate getCoordinate()
	{
		return coord;
	}
	
	@Override
	public Iterator<Coordinate> iterator()
	{
		return neighborsCoordinate.iterator();
	}

}
