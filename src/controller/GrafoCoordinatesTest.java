package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.Coordinate;

public class GrafoCoordinatesTest {


	@Test
	public void contieneAristaTest()
	{
		GrafoCoordinates grafo = instancia();
		
		assertTrue( grafo.contieneArista(new Coordinate(-34.52, -58.70), new Coordinate(-70.52, -40.70)));
		assertTrue( grafo.contieneArista(new Coordinate(-70.52, -40.70), new Coordinate(-96.52, -25.70)));
		assertTrue( grafo.contieneArista(new Coordinate(-45.52, -15.70), new Coordinate(-34.52, -58.70)));
	}

	@Test
	public void getPesoTest()
	{
		GrafoCoordinates grafo = instancia();
		
		assertEquals( 4150.56, grafo.getPeso(grafo.vertices().get(0), grafo.vertices().get(1)), 1e-4 );
		assertEquals( 6791.20, grafo.getPeso(grafo.vertices().get(3), grafo.vertices().get(1)), 1e-4 );
		assertEquals( 5666.48, grafo.getPeso(grafo.vertices().get(3), grafo.vertices().get(2)), 1e-4 );
		assertEquals( 3096.45, grafo.getPeso(grafo.vertices().get(1), grafo.vertices().get(2)), 1e-4 );
	}

	private GrafoCoordinates instancia() {
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
		
		GrafoCoordinates grafo = new GrafoCoordinates(new Coordinates());
		grafo.addArista(coordenadas.get(0), coordenadas.get(1));
		grafo.addArista(coordenadas.get(0), coordenadas.get(2));
		grafo.addArista(coordenadas.get(0), coordenadas.get(3));
		grafo.addArista(coordenadas.get(1), coordenadas.get(2));
		grafo.addArista(coordenadas.get(2), coordenadas.get(3));
		grafo.addArista(coordenadas.get(1), coordenadas.get(4));
		grafo.addArista(coordenadas.get(2), coordenadas.get(4));
		grafo.addArista(coordenadas.get(3), coordenadas.get(4));
		
		return grafo;
	}
}
