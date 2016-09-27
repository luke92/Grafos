package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import controller.Coordinates;
import domain.Coordinate;
import util.OfflineOsmTileSource;
import util.Proxy;

public class MainForm {
	private JFrame frame;
	private JMapViewer mapViewer;

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

		Proxy.autenticar();

		frame = new JFrame();
		frame.setBounds(400, 200, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		mapViewer = new JMapViewer();
		mapViewer.setTileSource(new OfflineOsmTileSource("file:///OSM/tiles", 1, 18));
		mapViewer.setZoomContolsVisible(false);
		mapViewer.setDisplayPositionByLatLon(-34.521, -58.7008, 11);

		Coordinates coordenadas = new Coordinates();
		coordenadas.readFile("instancias/instancia1.json");

		for (Coordinate coord : coordenadas) {
			double lat = coord.getLatitude();
			double lon = coord.getLongitude();
			mapViewer.addMapMarker(new MapMarkerDot(lat, lon));
		}

		frame.setContentPane(mapViewer);
	}
}
