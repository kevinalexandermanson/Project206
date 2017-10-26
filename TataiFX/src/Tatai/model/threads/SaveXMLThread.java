package Tatai.model.threads;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import Tatai.view.stats.PersonalStats;
import javafx.concurrent.Task;

public class SaveXMLThread extends Task<Void> {

	private static String sep = java.io.File.separator;
	private PersonalStats p1;
	
	public SaveXMLThread(PersonalStats p1) {
		this.p1 = p1;
	}
	
	@Override
	protected Void call() throws Exception {
		try{
			
			FileOutputStream fos = new FileOutputStream(new File(getJarPath() + sep + "PlayerData" + sep + p1.getPlayerName() + ".xml"));
			System.out.println("The folder path is: " + getJarPath() + sep + "PlayerData" + sep + p1.getPlayerName() + ".xml");
			
			XMLEncoder encoder = new XMLEncoder(fos);
			encoder.writeObject(p1);
			encoder.close();
			fos.close();
			System.out.println("Stats for "+ p1.getPlayerName() + " successfully saved.");

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
