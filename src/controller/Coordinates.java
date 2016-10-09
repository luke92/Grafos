package controller;

import java.util.ArrayList;

import java.util.Iterator;

import domain.Coordinate;

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
		if( index < 0 || index > size() )
			throw new RuntimeException("Indice fuera del rango: " + index);
		return _coordinates.get(index);
	}
	
	public int size()
	{
		return _coordinates.size();
	}
	
	public static double getPeso(Coordinate coord1, Coordinate coord2)
	{
		double radioTierra = 6371; // en kil�metros
		double dLat = Math.toRadians(coord2.getLat() - coord1.getLat());
		double dLng = Math.toRadians(coord2.getLon() - coord1.getLon());
		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);
		double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
				* Math.cos(Math.toRadians(coord1.getLat())) * Math.cos(Math.toRadians(coord2.getLat()));
		double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
		
		return radioTierra * va2;
	}

}