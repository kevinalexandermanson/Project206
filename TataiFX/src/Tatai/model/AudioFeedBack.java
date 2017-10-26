package Tatai.model;

import java.io.File;
import java.io.IOException;

import com.sun.media.jfxmedia.MediaPlayer;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

public class AudioFeedBack {

	private static final String MA = "ma";

	public static void playFeedBackAudio(int number) {

		Numbers[] maoriNumbers = Numbers.values();
		
		if (number <= 10) {
			for (Numbers maoriNumber : maoriNumbers) {
				if (number == maoriNumber.getIntegerValue()) {
					playAudio(maoriNumber.getNumber());
				}
			}
			
		}
		else if (number > 10 && number < 20) {
			number = number % 10;
			playAudio(Numbers.Ten.getNumber());
			playAudio(MA);
			for (Numbers maoriNumber : maoriNumbers) {
				if (number == maoriNumber.getIntegerValue()) {
					playAudio(maoriNumber.getNumber());
				}
			}
		}
		else if (number >= 20 && number < 100) {
			int lasDigit = number % 10;
			int firstDigit = number / 10;
			
			if (lasDigit == 0) {
				for (Numbers maoriNumber : maoriNumbers) {
					if (firstDigit == maoriNumber.getIntegerValue()) {
						playAudio(maoriNumber.getNumber());
					}
				}
				playAudio(Numbers.Ten.getNumber());
			}
			else {
				for (Numbers maoriNumber : maoriNumbers) {
					if (firstDigit == maoriNumber.getIntegerValue()) {
						playAudio(maoriNumber.getNumber());
					}
				}
				playAudio(Numbers.Ten.getNumber());
				playAudio(MA);
				for (Numbers maoriNumber : maoriNumbers) {
					if (lasDigit == maoriNumber.getIntegerValue()) {
						playAudio(maoriNumber.getNumber());
					}
				}
			}
		}
		
		
	}
	private static void playAudio(String number) {
		String musicFile =  "/Tatai/resources/Recordings/" + number + ".wav";
		
		AudioClip sound = new AudioClip(Tatai.view.Tatai.class.getResource(musicFile).toExternalForm());
		
		sound.play(); 
		
		//wait until sound finishes.
		while (sound.isPlaying()) {
			
		}
	}
	
	
	private static String getJarPath() {
		File jarLocation = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		return jarLocation.getAbsolutePath();
	}
	
}
