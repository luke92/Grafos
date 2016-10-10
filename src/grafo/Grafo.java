package grafo;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Grafo
{
	private ArrayList<Set<Integer>> vecinos; //vertices
	private int aristas;

	public Grafo(int n) //cantidad maxima de numeros
	{
		if (n < 0)
			throw new IllegalArgumentException("cantidad vertices negativo: " + n);
		
		vecinos = new ArrayList<Set<Integer>>();
		
		for(int i=0; i<n; ++i)
			vecinos.add( new HashSet<Integer>() );
	}

	public int vertices() //cantidadVertices
	{
		return vecinos.size();
	}

	public int aristas() //cantidad Aristas
	{
		return aristas;
	}

	public void agregarArista(int i, int j) //Agregar arista entre vertice i, y vertice j
	{
		chequearExtremos(i,j);

		if (!contieneArista(i,j))
			aristas++;
		
		vecinos.get(i).add(j);
		vecinos.get(j).add(i);
	}

	public void removerArista(int i, int j) //Remover arista entre vertice i y vertice j
	{
		chequearExtremos(i, j);

		if (contieneArista(i,j))
			aristas--;
		
		vecinos.get(i).remove(j);
		vecinos.get(j).remove(i);
	}

	public boolean contieneArista(int i, int j) //Chequear si existe arista entre vertice i y j
	{
		chequearExtremos(i,j);
		return vecinos.get(i).contains(j);
	}

	private void chequearExtremos(int i, int j) //chequear que no se agregue un bucle o que no se vaya del rango
	{
		if (i <= -1 || j <= -1 || i >= vertices() || j >= vertices())
			throw new IllegalArgumentException("Vertices fuera de rango: " + i + ", " + j + " (vertices = " + vertices() + ")");

		if (i == j)
			throw new IllegalArgumentException("No se pueden agregar loops: " + i);
	}

	public int gradoDelVertice(int v) //cantidad de vectores conectados a un vertice
	{
		return vecinos.get(v).size();
	}

	public Set<Integer> vecinos(int v) //devolver vectores conectados a un vector
	{
		return vecinos.get(v); // Taraaan!
	}
}
