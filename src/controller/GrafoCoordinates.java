package controller;

import java.util.ArrayList;

import domain.Coordinate;

public class GrafoCoordinates 
{
	private Coordinates coordinates;
	private ArrayList<NeighborsCoordinate> vertices;
	private int aristas;
	
	public GrafoCoordinates(Coordinates coords)
	{
		coordinates = coords;
		vertices = new ArrayList<NeighborsCoordinate>();
		aristas = 0;
		for(Coordinate coord: coords)
			vertices.add(new NeighborsCoordinate(coord));
	}
	
	public void addArista(Coordinate coord1, Coordinate coord2)
	{
		esBucle(coord1,coord2);
		int posCoord1 = indexVertice(coord1);
		int posCoord2 = indexVertice(coord2);
		if (posCoord1 >= 0 && posCoord2 >= 0 && !contieneArista(coord1, coord2)) {
			vertices.get(posCoord1).addNeighbor(coord2);
			vertices.get(posCoord2).addNeighbor(coord1);
			aristas++;
		}
	}
	
	public void removeArista(Coordinate coord1, Coordinate coord2)
	{
		esBucle(coord1, coord2);
		int posCoord1 = indexVertice(coord1);
		int posCoord2 = indexVertice(coord2);
		if (posCoord1 >= 0 && posCoord2 >= 0 && contieneArista(coord1, coord2)) {
			vertices.get(posCoord1).removeNeighbor(coord2);
			vertices.get(posCoord2).removeNeighbor(coord1);
			aristas--;
		}
	}
	
	public boolean contieneArista(Coordinate coord1, Coordinate coord2)
	{
		int posCoord1 = indexVertice(coord1);
		int posCoord2 = indexVertice(coord2);
		if(posCoord1 >= 0 && posCoord2 >= 0)
			return vertices.get(posCoord1).contains(coord2);
		
		return false;
	}
	
	private int indexVertice(Coordinate coord)
	{
		for(int i = 0; i < vertices.size(); i++) {
			if(vertices.get(i).equals(coord))
				return i;
		}
		
		return -1;
	}
	
	public int gradoDelVertice(Coordinate coord)
	{
		int posCoord = indexVertice(coord);
		if(posCoord >= 0)
			return vertices.get(posCoord).size();
		
		return -1;
	}
	
	private void esBucle(Coordinate coord1, Coordinate coord2)
	{
		if(coord1.equals(coord2))
			throw new IllegalArgumentException("No se pueden agregar loops: " + coord1);
	}
	
	public int aristas()
	{
		return aristas;
	}
	
	public Coordinates vertices()
	{
		return coordinates;
	}
	
	public double distanceCoord(Coordinate coord1, Coordinate coord2)
	{
		return Coordinates.distanceCoord(coord1, coord2);
	}
	
	public NeighborsCoordinate vecinos(Coordinate coord)
	{
		for(NeighborsCoordinate n : vertices) {
			if(n.equals(coord))
				return n;
		}
		return null;
	}
}
