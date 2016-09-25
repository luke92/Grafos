package domain;

import java.util.ArrayList;
import java.util.Iterator;

import dataAccess.DataAccessJSON;

public class Coordinates implements Iterable<Coordinate>
{
	private ArrayList<Coordinate> _coordinates;
	
	public Coordinates()
	{
		_coordinates = new ArrayList<Coordinate>();
	}
	
	public Coordinates(ArrayList<Coordinate> coordinates)
	{
		_coordinates = coordinates;
	}
	
	public void addCoordinate(Coordinate coord)
	{
		_coordinates.add(coord);
	}
	
	public void removeCoordinate(Coordinate coord)

	{
		_coordinates.remove(coord);
	}
	
	public boolean containsCoordinate(Coordinate coord)
	{
		for (Coordinate c: _coordinates){
			if(c.getLatitude()==coord.getLatitude() && c.getLongitude()==coord.getLongitude())
				return true;
		}
		return false;
	}
	
	public void readFile(String filename)
	{
		_coordinates= DataAccessJSON.readJSON(filename);
	}
	
	public void writeFile(String filename)
	{
		DataAccessJSON.writeJSON(filename, _coordinates);
	}
	
	@Override
	public String toString()
	{
		String ret = "";
		for(Coordinate coord : _coordinates)
		{
			ret+=coord;
			ret+="\n";
		}
		return ret;
	}

	@Override
	public Iterator<Coordinate> iterator()
	{
		return _coordinates.iterator();
	}
}
