package dataAccess;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import domain.Coordinate;
import domain.Coordinates;

public class DataAccessJSON 
{
	@SuppressWarnings("unchecked")
	public static ArrayList<Coordinate> readJSON(String filename)
	{
		
		Gson gson = new Gson();
		Coordinates ret = null;
		ArrayList<Coordinate> lcs = null;
		
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			Type collectionType = new TypeToken<List<Coordinate>>(){}.getType();
			lcs = (ArrayList<Coordinate>) new Gson().fromJson( br , collectionType);
		}
		catch (Exception e) { e.printStackTrace(); }
		return lcs;
	}
	
	public static void writeJSON(String filename, ArrayList<Coordinate> coordinates)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(coordinates);
		try
		{
			FileWriter writer = new FileWriter(filename);
			writer.write(json);
			writer.close();
		}
		catch(Exception e) { e.printStackTrace(); }
	}
}
