package controller;

import domain.Coordinate;

public class Algoritmos 
{
	public static GrafoCoordinates AGM(GrafoCoordinates grafo)
	{
		GrafoCoordinates resultado = new GrafoCoordinates( grafo.vertices() );
		NeighborsCoordinate amarillos = new NeighborsCoordinate();
		amarillos.add(grafo.vertices().get(0));	// ATENCION!
		
		for(int i = 0; i < grafo.vertices().size()-1; i++) {
			Arista a = menorArista(grafo, amarillos); // De un amarillo a un negro
			resultado.agregarArista(a.amarillo, a.negro);
			amarillos.add(a.negro);
		}
		
		return resultado;
	}
	
	static class Arista
	{
		public Coordinate amarillo;
		public Coordinate negro;
		public double peso;
		public Arista(Coordinate verticeAmarillo, Coordinate verticeNegro, double pesoArista)
		{
			amarillo = verticeAmarillo;
			negro = verticeNegro;
			peso = pesoArista;
		}
		
		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;

			if (obj == null || getClass() != obj.getClass())
				return false;
			
			Arista otra = (Arista) obj;
			return amarillo == otra.amarillo && negro == otra.negro;
		}
		
		@Override
		public String toString()
		{
			return amarillo + " " + negro + " " + peso;
		}
		// Retorna la arista de menor peso entre un vertice amarillo y uno no amarillo
		
	}
	
	static Arista menorArista(GrafoCoordinates grafo, NeighborsCoordinate amarillos)
	{
		Arista ret = new Arista(null, null, Double.MAX_VALUE);

		for (Coordinate i : amarillos)
		for (Coordinate j : grafo.vecinos(i))
			if ( !amarillos.contains(j) && grafo.getPeso(i, j)<ret.peso )
				ret = new Arista(i, j, grafo.getPeso(i, j));

		return ret;
	}
}
