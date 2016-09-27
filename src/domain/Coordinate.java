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
	
	public double getLatitude()
	{
		return latitud;
	}
	
	public double getLongitude()
	{
		return longitud;
	}
	
	@Override
	public String toString()
	{
		return "Latitud: " + latitud + ", Longitud: " + longitud;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(latitud);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitud);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (Double.doubleToLongBits(latitud) != Double.doubleToLongBits(other.latitud))
			return false;
		if (Double.doubleToLongBits(longitud) != Double.doubleToLongBits(other.longitud))
			return false;
		return true;
	}
	
	
}
