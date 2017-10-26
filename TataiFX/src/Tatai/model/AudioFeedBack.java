package Tatai.model;

import javafx.scene.media.AudioClip;

/** Handles playback of pronunciation **/
public class AudioFeedBack {

	private static final String MA = "ma";

	public static void playFeedBackAudio(int number) {

		Numbers[] maoriNumbers = Numbers.values();
		
		//Determines if the number is between 1-9 are plays the corresponding file
		if (number <= 10) {
			for (Numbers maoriNumber : maoriNumbers) {
				if (number == maoriNumber.getIntegerValue()) {
					playAudio(maoriNumber.getNumber());
				}
			}
			
		}
		//Determines if the number is between 11-19 are plays the corresponding files
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
		//Determines if the number is a multiple of 10 are plays the corresponding files
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
			// All other numbers will then be played with the usual structure
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
	
	/**
	 * Plays the corresponding pronunciation file according to the inputed number
	 * @param number
	 */
	private static void playAudio(String number) {
		String musicFile =  "/Tatai/resources/Recordings/" + number + ".wav";
		
		AudioClip sound = new AudioClip(Tatai.view.Tatai.class.getResource(musicFile).toExternalForm());
		
		sound.play(); 
		
		//wait until sound finishes.
		while (sound.isPlaying()) {
			
		}
	}
	
	
}
