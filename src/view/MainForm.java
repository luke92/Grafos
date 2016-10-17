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

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;

public class MainForm {
	private JFrame frame;
	private JMapViewer mapViewer;
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
//		mapViewer.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				System.out.println(mapViewer.getPosition(e.getPoint()));
//			}
//		});
		// mapViewer.setTileSource(new OfflineOsmTileSource("file:///OSM/tiles", 1, 18));
		mapViewer.setZoomContolsVisible(false);
		mapViewer.setDisplayPositionByLatLon(-34.521, -58.7008, 11);

		frame.setContentPane(mapViewer);

		JButton btnAbrirInstancias = new JButton("Abrir Instancias");
		btnAbrirInstancias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarCoordenadas();
			}
		});

		btnAbrirInstancias.setBounds(817, 11, 157, 23);
		mapViewer.add(btnAbrirInstancias);

		JButton btnBorrarMarcadores = new JButton("Borrar Marcadores");
		btnBorrarMarcadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				borrarObjetosMapa();
			}
		});

		btnBorrarMarcadores.setBounds(817, 45, 157, 23);
		mapViewer.add(btnBorrarMarcadores);
	}
	
	private void cargarCoordenadas() 
	{
		listCoords = OpenFilesForm.getListCoordinates(mapViewer);
		for (Coordinates coords : listCoords)
			for (Coordinate c : coords)
				mapViewer.addMapMarker(new MapMarkerDot(c.getLat(), c.getLon()));
		
		dibujarPoligonos();
	}

	private void borrarObjetosMapa() 
	{
		mapViewer.removeAllMapMarkers();
		mapViewer.removeAllMapPolygons();
		mapViewer.removeAllMapRectangles();
		listCoords = new ArrayList<Coordinates>();
	}

	private void dibujarPoligonos() 
	{
		for (Coordinates coords : listCoords) {
			GrafoCoordinates grafo = new GrafoCoordinates(coords);
			grafo.agregarTodasAristas();
			GrafoCoordinates agm = Algoritmos.AGM(grafo);
			Integer cantClusters = escribirCantidadClusters(agm.aristas());
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
	
	private Integer escribirCantidadClusters(int aristas)
	{
		String nombre = JOptionPane.showInputDialog("Cuantos Clusters quiere generar? (1 a " + aristas + ")");
		Integer cantidad = 0;
		if (!Pattern.matches("[1-9]\\d*", nombre))
			escribirCantidadClusters(aristas);
		
		cantidad = Integer.parseInt(nombre);
		if (cantidad > aristas)
			cantidad = escribirCantidadClusters(aristas);
		return cantidad;
	}

}
