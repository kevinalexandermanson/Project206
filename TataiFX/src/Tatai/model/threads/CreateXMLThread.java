package Tatai.model.threads;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import Tatai.view.stats.PersonalStats;
import javafx.concurrent.Task;

/**
 * Handles the thread for creating new xml files for users
 *
 */
public class CreateXMLThread extends Task<Void> {
	
	private static String sep = java.io.File.separator;
	private String name;
	
	public CreateXMLThread(String name){
		this.name = name;
	}

	@Override
	protected Void call() throws Exception {
		try{
			//Create the new player object.
			PersonalStats p1 = new PersonalStats(name);
			
			FileOutputStream fos = new FileOutputStream(new File(getJarPath() + sep + "PlayerData" + sep + p1.getPlayerName() + ".xml"));
			
			XMLEncoder encoder = new XMLEncoder(fos);
			encoder.writeObject(p1);
			encoder.close();
			fos.close();
			System.out.println("New player: "+ p1.getPlayerName() + " successfully created.");

		} catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Returns the location the application is being run from as a String. Does not have a / at the end!
	 */
	private static String getJarPath() {
		File jarLocation = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		return jarLocation.getAbsolutePath();
	}
}
