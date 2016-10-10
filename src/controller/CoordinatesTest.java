package controller;

import static org.junit.Assert.*;

import org.junit.Test;
import domain.Coordinate;

public class CoordinatesTest {
	
	private Coordinates instancia()
	{
		Coordinates c= new Coordinates();
		c.addCoordinate(new Coordinate(20.5, 20));
		c.addCoordinate(new Coordinate(40,40));
		c.addCoordinate(new Coordinate(60,60));
		c.addCoordinate(new Coordinate(-34.58, -58.34));
		
		return c;
	}
	
	@Test
	public void addCoordinateTest()
	{
		Coordinates c= instancia();

		assertTrue( c.containsCoordinate( new Coordinate(20.5, 20) ));
		assertTrue( c.containsCoordinate( new Coordinate(60,60) ));
		assertTrue( c.containsCoordinate( new Coordinate(-34.58, -58.34) ));
		
		assertFalse( c.containsCoordinate( new Coordinate(20,60) ));
		assertFalse( c.containsCoordinate( new Coordinate(-34.59, -58.35) ));
		
		c.addCoordinate(new Coordinate(20,60) );
		assertTrue( c.containsCoordinate( new Coordinate(20,60) ));
	}

	@Test
	public void removeCoordinateTest()
	{
		Coordinates c= instancia();
		
		assertTrue( c.containsCoordinate( new Coordinate(20.5, 20)) );
		c.removeCoordinate( new Coordinate(20.5, 20) );
		assertFalse( c.containsCoordinate( new Coordinate(20.5, 20)) );
	}
	
	@Test
	public void getPesoTest()
	{
		assertEquals( 4146.364904306314, Coordinates.getPeso(new Coordinate(-34.52, -58.70), new Coordinate(-70.52, -40.70)), 1e-4 );
	}
	
}
