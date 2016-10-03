package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import controller.Coordinates;
import domain.Coordinate;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

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

		// Proxy.autenticar();

		frame = new JFrame();
		frame.setBounds(400, 200, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		mapViewer = new JMapViewer();
		// mapViewer.setTileSource(new OfflineOsmTileSource("file:///OSM/tiles",
		// 1, 18));
		mapViewer.setZoomContolsVisible(false);
		mapViewer.setDisplayPositionByLatLon(-34.521, -58.7008, 11);

		frame.setContentPane(mapViewer);

		JButton btnAbrirInstancias = new JButton("Abrir Instancias");
		btnAbrirInstancias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Seleccionar archivos
				List<String> rutas = OpenFilesForm.getPathSelectFiles(mapViewer);
				Coordinates coordenadas = new Coordinates();
				for (String x : rutas) {
					coordenadas.readFile(x);
					for (Coordinate coord : coordenadas) {
						double lat = coord.getLatitude();
						double lon = coord.getLongitude();
						mapViewer.addMapMarker(new MapMarkerDot(lat, lon));
					}
				}
			}
		});

		btnAbrirInstancias.setBounds(817, 11, 157, 23);
		mapViewer.add(btnAbrirInstancias);

		JButton btnBorrarMarcadores = new JButton("Borrar Marcadores");
		btnBorrarMarcadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mapViewer.removeAllMapMarkers();
				mapViewer.removeAllMapPolygons();
				mapViewer.removeAllMapRectangles();

			}
		});

		btnBorrarMarcadores.setBounds(817, 45, 157, 23);
		mapViewer.add(btnBorrarMarcadores);
	}

}
