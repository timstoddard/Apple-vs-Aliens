package game.gamerunner.sound;

import java.io.*;
import javax.sound.sampled.*;
import javax.sound.sampled.LineEvent.Type;

public class SoundPlayer implements Runnable {

	private SourceDataLine line = null;
	private byte[] audioBytes;
	private int numBytes;
	private Reader r;
	private short[] volumes; // 0 = music, 1 = sound

	public SoundPlayer(String fileName) {
		File soundFile = new File(fileName);
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
//			Clip clip = AudioSystem.getClip();
//			clip.open(audioInputStream);
//			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
//			float value = gainControl.getValue();
//			System.out.println(value+" // "+gainControl.getMinimum()+" // "+gainControl.getMaximum());
//			gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
//			clip.close();
		} catch (IOException ex) {
			System.out.println("*** Cannot find " + fileName + " ***");
			System.exit(1);
		} catch (UnsupportedAudioFileException t) {
			System.out.println("System did not recognize the audio file...");
			System.exit(1);
//		} catch (LineUnavailableException e) {
//			System.out.println("The line was unavailable...");
//			System.exit(1);
		}

		AudioFormat audioFormat = audioInputStream.getFormat();
		DataLine.Info info = new DataLine.Info(SourceDataLine.class,
				audioFormat);
		try {
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(audioFormat);
		} catch (LineUnavailableException ex) {
			System.out.println("*** Audio line unavailable ***");
			System.exit(1);
		}

		line.start();

		audioBytes = new byte[(int) soundFile.length()];

		try {
			numBytes = audioInputStream.read(audioBytes, 0, audioBytes.length);
		} catch (IOException ex) {
			System.out.println("*** Cannot read " + fileName + " ***");
			System.exit(1);
		}
		//AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("some_file.wav"));
		//	Clip clip = AudioSystem.getClip();
		//	clip.open(audioInputStream);
		//	FloatControl gainControl = 
		//	    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		//	gainControl.setValue(-10.0f); // Reduce volume by 10 decibels.
	}

	public void run() {
		while (true) {
			line.write(audioBytes, 0, numBytes);
		}
	}

	public void stop() {
		line.flush();
	}

	public void loop() {
		new Thread(this).start();
	}
	
	public void play() {
		line.write(audioBytes, 0, numBytes);
	}
}