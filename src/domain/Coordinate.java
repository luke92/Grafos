package domain;

public class Coordinate 
{
	private double latitud;
	private double longitud;
	
	public Coordinate (double latitude, double longitude)
	{
		latitud = latitude;
		longitud = longitude;
	}
	
	@Override
	public String toString()
	{
		return "Latitud: " + latitud + ", Longitud: " + longitud;
	}
}
