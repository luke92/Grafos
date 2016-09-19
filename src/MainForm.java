import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

public class MainForm
{
	private JFrame frame;
	private JMapViewer map;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MainForm window = new MainForm();
					window.frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public MainForm()
	{
		initialize();
	}

	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(400, 200, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		map = new JMapViewer();
//		map.setTileSource(new OfflineOsmTileSource("buenos-aires_argentina.mbtiles",1,9));
		map.setZoomContolsVisible(false);
		map.setDisplayPositionByLatLon(-34.521, -58.7008, 11);

		// Ponemos un marcador!
		MapMarker marker = new MapMarkerDot(-34.521, -58.7008);
		marker.getStyle().setBackColor(Color.RED);
		map.addMapMarker(marker);
		
		// Ahora un polígono!
		ArrayList<Coordinate> coordenadas = new ArrayList<Coordinate>();
		coordenadas.add(new Coordinate(-34.532, -58.7128));
		coordenadas.add(new Coordinate(-34.546, -58.719));
		coordenadas.add(new Coordinate(-34.559, -58.721));
		coordenadas.add(new Coordinate(-34.569, -58.725));
		coordenadas.add(new Coordinate(-34.532, -58.730));
		
		MapPolygon polygon = new MapPolygonImpl(coordenadas);
		map.addMapPolygon(polygon);
		
		// Y un marcador en cada vértice del polígono!
		for(Coordinate c: coordenadas)
			map.addMapMarker(new MapMarkerDot(c));
		
		frame.setContentPane(map);
	}
}
