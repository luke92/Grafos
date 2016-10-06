package controller;

import java.util.ArrayList;

import domain.Coordinate;

public class GrafoCoordinates 
{
	private ArrayList<NeighborsCoordinate> vertices;
	private int aristas;
	
	public GrafoCoordinates()
	{
		vertices = new ArrayList<NeighborsCoordinate>();
		aristas = 0;
	}
	
	public void addCoordinates(Coordinates coords)
	{
		for(Coordinate coord: coords)
			vertices.add(new NeighborsCoordinate(coord));
	}
	
	public void addArista(Coordinate coord1, Coordinate coord2)
	{
		esBucle(coord1,coord2);
		int posCoord1 = indexNeighbor(coord1);
		int posCoord2 = indexNeighbor(coord2);
		if(posCoord1 >= 0 && posCoord2 >= 0)
		{
			if(!contieneArista(vertices.get(posCoord1),coord2))
			{
				vertices.get(posCoord1).addNeighbor(coord2);
				vertices.get(posCoord2).addNeighbor(coord1);
				aristas++;
			}
		}
	}
	
	public void removeArista(Coordinate coord1, Coordinate coord2)
	{
		esBucle(coord1, coord2);
		int posCoord1 = indexNeighbor(coord1);
		int posCoord2 = indexNeighbor(coord2);
		if(posCoord1 >= 0 && posCoord2 >= 0){
			if(contieneArista(vertices.get(posCoord1),coord2)){
				vertices.get(posCoord1).removeNeighbor(coord2);
				vertices.get(posCoord2).removeNeighbor(coord1);
				aristas--;
			}
		}
	}
	
	public boolean contieneArista(NeighborsCoordinate coord1, Coordinate coord2)
	{
		return coord1.contains(coord2);
	}
	
	private int indexNeighbor(Coordinate coord)
	{
		for(int i = 0; i < vertices.size(); i++)
		{
			if(vertices.get(i).equals(coord))
				return i;
		}
		return -1;
	}
	
	public int aristas()
	{
		return aristas;
	}
	
	public int vertices()
	{
		return vertices.size();
	}
	
	public int gradoDelVertice(Coordinate coord)
	{
		int posCoord = indexNeighbor(coord);
		if(posCoord >= 0)
			return vertices.get(posCoord).size();
		return -1;
	}
	
	public void esBucle(Coordinate coord1, Coordinate coord2)
	{
		throw new IllegalArgumentException("No se pueden agregar loops: " + coord1);
	}
}
