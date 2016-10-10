package controller;

import static org.junit.Assert.*;
import org.junit.Test;

import domain.Coordinate;

public class AlgoritmosTest
{
	private Coordinates instanciaCoordenadas()
	{
		Coordinate c0= new Coordinate(-34.52, -58.70);
		Coordinate c1= new Coordinate(-70.52, -40.70);
		Coordinate c2= new Coordinate(-45.52, -15.70);
		Coordinate c3= new Coordinate(-96.52, -25.70);
		Coordinate c4= new Coordinate(-24.52, -35.70);
		
		Coordinates coordenadas = new Coordinates();
		coordenadas.addCoordinate(c0);
		coordenadas.addCoordinate(c1);
		coordenadas.addCoordinate(c2);
		coordenadas.addCoordinate(c3);
		coordenadas.addCoordinate(c4);
		
		return coordenadas;
	}
	
	private GrafoCoordinates instanciaSinAristas() 
	{
		GrafoCoordinates grafo = new GrafoCoordinates(instanciaCoordenadas());
	
		return grafo;
	}
	
	private GrafoCoordinates instanciaConAristas()
	{
		
		GrafoCoordinates grafo = instanciaSinAristas();
		Coordinates coordenadas = instanciaCoordenadas();
		grafo.agregarArista(coordenadas.get(0), coordenadas.get(1));
		grafo.agregarArista(coordenadas.get(0), coordenadas.get(2));
		grafo.agregarArista(coordenadas.get(0), coordenadas.get(3));
		grafo.agregarArista(coordenadas.get(1), coordenadas.get(2));
		grafo.agregarArista(coordenadas.get(2), coordenadas.get(3));
		grafo.agregarArista(coordenadas.get(1), coordenadas.get(4));
		grafo.agregarArista(coordenadas.get(2), coordenadas.get(4));
		grafo.agregarArista(coordenadas.get(3), coordenadas.get(4));
		
		return grafo;
	}
	
	private GrafoCoordinates instancia() 
	{
		return instanciaConAristas();
	}
	
	@Test
	public void unAmarilloTest()
	{
		GrafoCoordinates grafo = instancia();

		NeighborsCoordinate amarillos = new NeighborsCoordinate(grafo.vertices().get(0));
		amarillos.add(grafo.vertices().get(0));
		
		Algoritmos.Arista arista= Algoritmos.menorArista(grafo, amarillos);
		
		assertEquals(new Algoritmos.Arista(new Coordinate(-34.52,-58.70), new Coordinate(-70.52,-40.70), 4146.364904306314 ), arista);
	}
	
	@Test
	public void tresAmarillosTest()
	{
		GrafoCoordinates grafo = instancia();

		NeighborsCoordinate amarillos = new NeighborsCoordinate(null);
		amarillos.add(grafo.vertices().get(0));
		amarillos.add(grafo.vertices().get(1));
		amarillos.add(grafo.vertices().get(2));
		
		Algoritmos.Arista arista = Algoritmos.menorArista(grafo, amarillos);
		assertEquals(new Algoritmos.Arista(new Coordinate(-70.52, -40.70), new Coordinate(-24.52, -35.70), 5130.12), arista);
	}

	@Test
	public void primTest()
	{
		GrafoCoordinates grafo = instancia();
		GrafoCoordinates agm = Algoritmos.AGM(grafo);
		
		assertTrue(agm.contieneArista(new Coordinate(-34.52, -58.70), new Coordinate(-70.52, -40.70)));
		assertTrue(agm.contieneArista(new Coordinate(-34.52, -58.70), new Coordinate(-96.52, -25.70)));
		assertTrue(agm.contieneArista(new Coordinate(-96.52, -25.70), new Coordinate(-45.52, -15.70)));
		assertTrue(agm.contieneArista(new Coordinate(-24.52, -35.70), new Coordinate(-70.52, -40.70)));
		
		assertEquals(4, agm.aristas());		
	}
}
