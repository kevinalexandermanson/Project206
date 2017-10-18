package Tatai.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Recording {
	public Recording() {

	}

	/**
	 * Records user using ffmpeg
	 */
	public void record() {
		try {
			String cmd = "ffmpeg -f alsa -i default -t 3 -acodec pcm_s16le -ar 22050 -ac 1 -y "+ getJarPath() + "/out.wav";

			ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", cmd);

			Process process = builder.start();

			process.waitFor();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// HTK command
		try {
			String cmd = "HVite -H /home/se206/Documents/HTK/MaoriNumbers/HMMs/hmm15/macros -H /home/se206/Documents/HTK/MaoriNumbers/HMMs/hmm15/hmmdefs -C /home/se206/Documents/HTK/MaoriNumbers/user/configLR -w /home/se206/Documents/HTK/MaoriNumbers/user/wordNetworkNum -o SWT -l '*' -i " + getJarPath() + "/recout.mlf -p 0.0 -s 5.0  /home/se206/Documents/HTK/MaoriNumbers/user/dictionaryD /home/se206/Documents/HTK/MaoriNumbers/user/tiedList " + getJarPath() + "/out.wav";

			ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", cmd);

			Process process = builder.start();

			process.waitFor();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Plays recording using aplay
	 */
	public void playRecording() {
		try {
			String cmd = "aplay " + getJarPath() + "/out.wav";

			ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", cmd);

			Process process = builder.start();
			
			process.waitFor();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	/**
	 * Gets the correct maori number based on integer input.
	 */
	public String getCorrectNumber(int number) {
		Numbers[] maoriNumbers = Numbers.values();
		
		if (number <= 10) {
			//return numbers from 1 to 10
			for (Numbers maoriNumber : maoriNumbers) {
				if (number == maoriNumber.getIntegerValue()) {
					return maoriNumber.getNumber();
				}
			}
			
		} else if (number > 10 && number < 20) {
			//return numbers from 11 to 19
			number = number % 10;
			for (Numbers maoriNumber : maoriNumbers) {
				if (number == maoriNumber.getIntegerValue()) {
					return Numbers.Ten.getNumber() + " " + "maa" + " " + maoriNumber.getNumber();
				}
			}
			
		} else if (number >= 20 && number < 100) {
			//return numbers from 20 - 99
			
			//get the tens column
			int tens = number / 10;
			
			//if it is 10, 20, 30 etc.
			if (number % 10 == 0) {
				for (Numbers maoriNumber : maoriNumbers) {
					if (tens == maoriNumber.getIntegerValue()) {
						return maoriNumber.getNumber() + " " + Numbers.Ten.getNumber();
					}
				}
			} else {
				//get the ones column
				int ones = number % 10;
				
				//strings for the maori of the corresponding columns
				String maoriTens = "";
				String maoriOnes = "";
				
				for (Numbers maoriNumber : maoriNumbers) {
					if (tens == maoriNumber.getIntegerValue()) {
						maoriTens = maoriNumber.getNumber() + " " + Numbers.Ten.getNumber();
					}
				}
				
				for (Numbers maoriNumber : maoriNumbers) {
					if (ones == maoriNumber.getIntegerValue()) {
						maoriOnes = maoriNumber.getNumber();
					}
				}
				
				return maoriTens + " " + "maa" + " " + maoriOnes;		
				
			}
		}
		
		
		
		return "";
	}
	
	/**
	 * Gets the recorded number that the user said.
	 */
	public String getRecordedNumber() { 
		String recordedNumber = "";
		
		try {
			String cmd = "sed '/sil/,/sil/!d' " + getJarPath() + "/recout.mlf";

			ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", cmd);

			Process process = builder.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line = reader.readLine();
			
			while (line != null) {

				if (!(line.equals("sil"))) {
					if (recordedNumber.equals("")) {
						recordedNumber = line;
					}
					else {
						recordedNumber = recordedNumber + " " + line;
					}
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return recordedNumber;
	}
	

	private String getJarPath() {
		File jarLocation = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		return jarLocation.getAbsolutePath();
	}

}
