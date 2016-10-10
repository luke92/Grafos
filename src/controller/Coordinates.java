package controller;

import java.util.ArrayList;

import java.util.Iterator;

import domain.Coordinate;
import util.DistanceCalculator;
import dataAccess.DataAccessJSON;

public class Coordinates implements Iterable<Coordinate> {
	private ArrayList<Coordinate> _coordinates;

	public ArrayList<Coordinate> getCoords()
	{
		return _coordinates;
	}

	public Coordinates()
	{
		_coordinates = new ArrayList<Coordinate>();
	}

	public Coordinates(ArrayList<Coordinate> coordinates)
	{
		_coordinates = coordinates;
	}

	public Coordinates(String filename)
	{
		readFile(filename);
	}

	public void addCoordinate(Coordinate coord)
	{
		_coordinates.add(coord);
	}

	public void removeCoordinate(Coordinate coord) {
		_coordinates.remove(coord);
	}

	public boolean containsCoordinate(Coordinate coord)
	{
		for (Coordinate c : _coordinates)
			if (c.equals(coord))
				return true;

		return false;
	}

	public boolean readFile(String filename)
	{
		_coordinates = DataAccessJSON.readJSON(filename);
		if (_coordinates == null)
			return false;
		return true;
	}

	public boolean writeFile(String filename)
	{
		return DataAccessJSON.writeJSON(filename, _coordinates);
	}

	@Override
	public String toString()
	{
		String ret = "";
		for (Coordinate coord : _coordinates){
			ret += coord;
			ret += "\n";
		}
		return ret;
	}

	@Override
	public Iterator<Coordinate> iterator()
	{
		return _coordinates.iterator();
	}
	
	public Coordinate get(int index)
	{
		if( index < 0 || index > size()-1 )
			throw new RuntimeException("Indice fuera del rango: " + index);
		return _coordinates.get(index);
	}
	
	public int size()
	{
		return _coordinates.size();
	}
	
	public static double getPeso(Coordinate coord1, Coordinate coord2)
	{
		return DistanceCalculator.distance(coord1.getLat(), coord1.getLon(), coord2.getLat(), coord2.getLon(), "K");
	}

}