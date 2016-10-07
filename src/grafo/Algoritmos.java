package grafo;


import java.util.HashSet;
import java.util.Set;

public class Algoritmos
{
	// Algoritmo de Prim
	public static GrafoPesado AGM(GrafoPesado grafo)
	{
		GrafoPesado resultado = new GrafoPesado(grafo.vertices());
		Set<Integer> amarillos = new HashSet<Integer>();
		amarillos.add(0); // Cualquiera
		
		for(int i=0; i<grafo.vertices()-1; ++i) {
			Arista a = menorArista(grafo, amarillos); // De un amarillo a un negro
			resultado.agregarArista(a.amarillo, a.negro, a.peso);
			amarillos.add(a.negro);
		}
		
		return resultado;
	}
	
	// Inner class
	static class Arista
	{
		public int amarillo;
		public int negro;		
		public double peso;
		
		public Arista(int verticeAmarillo, int verticeNegro, double pesoArista)
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
	}

	// Retorna la arista de menor peso entre un vertice amarillo y uno no amarillo
	static Arista menorArista(GrafoPesado grafo, Set<Integer> amarillos)
	{
		Arista ret = new Arista(0, 0, Double.MAX_VALUE);
		
		for(Integer i: amarillos)
		for(Integer j: grafo.vecinos(i))
			if( amarillos.contains(j) == false && grafo.getPeso(i, j) < ret.peso )
				ret = new Arista(i, j, grafo.getPeso(i, j));
		
		return ret;
	}
}