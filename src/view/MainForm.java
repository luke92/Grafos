package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import domain.Coordinate;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import controller.Algoritmos;
import controller.Cluster;
import controller.Coordinates;
import controller.GrafoCoordinates;
import controller.MapController;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class MainForm {
	private JFrame frame;
	private JMapViewer mapViewer;
	private MapController mapController;
	List<Coordinates> listCoords;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainForm() {
		initialize();
	}

	private void initialize() {
		// Proxy.autenticar();
		
		frame = new JFrame();
		frame.setBounds(400, 200, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		mapViewer = new JMapViewer();
		mapViewer.setZoomContolsVisible(false);
		mapViewer.setDisplayPositionByLatLon(-34.521, -58.7008, 11);

		frame.setContentPane(mapViewer);

		JButton btnCargarInstancias = new JButton("Cargar Instancias");
		btnCargarInstancias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarInstancias();
			}
		});

		btnCargarInstancias.setBounds(817, 11, 157, 23);
		mapViewer.add(btnCargarInstancias);

		JButton btnBorrarInstancias = new JButton("Borrar Instancias");
		btnBorrarInstancias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				borrarInstancias();
			}
		});

		btnBorrarInstancias.setBounds(817, 45, 157, 23);
		mapViewer.add(btnBorrarInstancias);

		JButton btnAllClusters = new JButton("Generar todos los Clusters");
		btnAllClusters.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				mapController.generarAllClusters();
				List<Coordinates> listaCoords= listCoords;
				borrarInstancias();
				cargarCoordenada(listaCoords);
				dibujarPoligono(listaCoords);
				listCoords= listaCoords;
			}
		});

		btnAllClusters.setBounds(790, 79, 190, 23);
		mapViewer.add(btnAllClusters);
		
		mapController = new MapController(mapViewer);
	}
	
	private void cargarInstancias() 
	{
		mapController.cargarInstancias();
	}
	
	private void cargarCoordenada(List<Coordinates> listCoords) 
	{
		for (Coordinates coords : listCoords)
			for (Coordinate c : coords)
				mapViewer.addMapMarker(new MapMarkerDot(c.getLat(), c.getLon()));

		dibujarPoligonos();
	}
	
	private void borrarInstancias() 
	{
		mapController.borrarInstancias();
	}

	private void dibujarPoligonos() 
	{
		int index= -1;
		for (Coordinates coords : listCoords) {
			index++;
			GrafoCoordinates grafo = new GrafoCoordinates(coords);
			grafo.agregarTodasAristas();
			GrafoCoordinates agm = Algoritmos.AGM(grafo);
			Integer cantClusters = escribirCantidadClusters(agm.aristas(), index);
			Cluster.generar(agm, cantClusters);
			for (Coordinate c1 : agm.vertices())
				for (Coordinate c2 : agm.vecinos(c1)) {
					ArrayList<Coordinate> aristaMapa = new ArrayList<Coordinate>();
					aristaMapa.add(c1);
					aristaMapa.add(c2);
					aristaMapa.add(c2);
					mapViewer.addMapPolygon(new MapPolygonImpl(aristaMapa));
				}
		}
	}
	
	private void dibujarPoligono(List<Coordinates> listCoords) 
	{
		int index= -1;
		for (Coordinates coords : listCoords) {
			index++;
			GrafoCoordinates grafo = new GrafoCoordinates(coords);
			grafo.agregarTodasAristas();
			GrafoCoordinates agm = Algoritmos.AGM(grafo);
			Integer cantClusters = escribirCantidadClusters(agm.aristas(), index);
			if(cantClusters > 0) Cluster.generar(agm, cantClusters);
			for (Coordinate c1 : agm.vertices())
				for (Coordinate c2 : agm.vecinos(c1)) {
					ArrayList<Coordinate> aristaMapa = new ArrayList<Coordinate>();
					aristaMapa.add(c1);
					aristaMapa.add(c2);
					aristaMapa.add(c2);
					mapViewer.addMapPolygon(new MapPolygonImpl(aristaMapa));
				}
		}
	}
	
	private Integer escribirCantidadClusters(int aristas, int index)
	{
		String nombre = "";
		//String nombre = JOptionPane.showInputDialog("Cuantos Clusters quiere generar? (1 a " + (aristas+1) + ") para " + OpenFilesForm.getNombre(index));
		Integer cantidad = 0;

		if (!Pattern.matches("[1-9]\\d*", nombre))
			escribirCantidadClusters(aristas, index);

		cantidad = Integer.parseInt(nombre);
		if (cantidad > aristas+1)
			cantidad = escribirCantidadClusters(aristas, index);

		return cantidad;
	}

}
