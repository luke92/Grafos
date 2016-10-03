package view;

import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Coordinates;

public class OpenFilesForm 
{
	public static Coordinates getCoordinates()
	{
		return null;
	}
	
	public static List<String> getPathSelectFiles(Component contentPane)
	{
		// returns the current working directory as a String
		String rutaProjecto = System.getProperty("user.dir");
		
		JFileChooser fc=new JFileChooser(new File(rutaProjecto));
   	 	
    	//Indicamos que podemos seleccionar varios ficheros
    	fc.setMultiSelectionEnabled(true);
    	 
    	//Indicamos lo que podemos seleccionar
    	fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    	 
    	//Creamos el filtro
    	FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.json", "JSON");
    	 
    	//Le indicamos el filtro
    	fc.setFileFilter(filtro);
    	 
    	//Abrimos la ventana, guardamos la opcion seleccionada por el usuario
    	int seleccion=fc.showOpenDialog(contentPane);
    	 
    	//Si el usuario, pincha en aceptar
    	if(seleccion==JFileChooser.APPROVE_OPTION)
    	{
    		List<String> archivos = new ArrayList<String>();
    	    //Seleccionamos el fichero
    	    File[] ficheros=fc.getSelectedFiles();
    	    for(int i=0;i<ficheros.length;i++)
    	    {
    	    	archivos.add(ficheros[i].getAbsolutePath());
    	    }
    	    return archivos;
    	}
    	return null;
	}
}