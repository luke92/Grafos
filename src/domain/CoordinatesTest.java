package domain;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

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
	
	private File crearArchivo()
	{
		File a= new File("archivo.txt");
		return a;
	}
	
	@Test
	public void addCoordinate()
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
	public void removeCoordinate()
	{
		Coordinates c= instancia();
		
		assertTrue( c.containsCoordinate( new Coordinate(20.5, 20)) );
		c.removeCoordinate( new Coordinate(20.5, 20) );
		assertTrue( c.containsCoordinate( new Coordinate(20.5, 20)) );
	}
	
	@Test
	public void readFileTest()
	{
        File a= crearArchivo();
        BufferedWriter bw;
        try {
			bw = new BufferedWriter(new FileWriter(a));
			bw.write("El fichero de texto ya estaba creado.");
		}
        catch (IOException e) { e.printStackTrace(); }
        
        
        assertTrue(a.canRead());
	}
	
	@Test
	public void writeFileTest()
	{
		
	}
}
