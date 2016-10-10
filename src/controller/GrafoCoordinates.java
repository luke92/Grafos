package controller;

import java.util.ArrayList;

import domain.Coordinate;

public class GrafoCoordinates 
{
	private ArrayList<NeighborsCoordinate> vecinos;
	private int aristas;
	
	public GrafoCoordinates(Coordinates coords)
	{
		if(coords.size() < 2) 
			throw new IllegalArgumentException("No se puede crear un grafo con menos de 2 elementos");
		vecinos = new ArrayList<NeighborsCoordinate>();
		aristas = 0;
		for(Coordinate coord: coords)
			vecinos.add(new NeighborsCoordinate(coord));
	}
	
	public int vertices() //cantidadVertices
	{
		return vecinos.size();
	}
	
	public int aristas()
	{
		return aristas;
	}
	
	public void agregarArista(Coordinate coord1, Coordinate coord2)
	{
		chequearExtremos(coord1,coord2);
		
		if(!contieneArista(coord1, coord2))
			aristas++;
		
		vecinos.get(vecinos.indexOf(coord1)).add(coord2);
		vecinos.get(vecinos.indexOf(coord2)).add(coord1);
		
	}
	
	public void removerArista(Coordinate coord1, Coordinate coord2)
	{
		chequearExtremos(coord1, coord2);
		
		if(contieneArista(coord1, coord2))
			aristas--;
		
		vecinos.get(vecinos.indexOf(coord1)).remove(coord2);
		vecinos.get(vecinos.indexOf(coord2)).remove(coord1);
		
		
	}
	
	public boolean contieneArista(Coordinate coord1, Coordinate coord2)
	{
		chequearExtremos(coord1, coord2);
		return vecinos.get(vecinos.indexOf(coord1)).contains(coord2);
	}
	
	private void chequearExtremos(Coordinate coord1, Coordinate coord2)
	{
		if(vecinos.indexOf(coord1) < 0 || vecinos.indexOf(coord2) < 0)
			throw new IllegalArgumentException("Verices fuera del rango: " + coord1 + coord2);
		if(coord1.equals(coord2))
			throw new IllegalArgumentException("No se pueden agregar loops: " + coord1);
	}
	
	public int gradoDelVertice(Coordinate coord)
	{
		int posCoord = vecinos.indexOf(coord);
		if(posCoord >= 0)
			return vecinos.get(posCoord).size();
		
		return -1;
	}
	
	public double getPeso(Coordinate coord1, Coordinate coord2)
	{
		if(!contieneArista(coord1, coord2))
			throw new IllegalArgumentException("Se consulto el peso de una arista inexistente : " + coord1 + coord2);
		
		return Coordinates.getPeso(coord1, coord2);
	}
	
	public NeighborsCoordinate vecinos(Coordinate coord)
	{
		for(NeighborsCoordinate n : vecinos) {
			if(n.equals(coord))
				return n;
		}
		return null;
	}
	
}
